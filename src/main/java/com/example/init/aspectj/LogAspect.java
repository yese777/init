package com.example.init.aspectj;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.enums.ApiErrorCode;
import com.example.init.annotation.Log;
import com.example.init.entity.SysLog;
import com.example.init.utils.ServletUtils;
import com.example.init.utils.ip.AddressUtils;
import com.example.init.utils.ip.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.StringJoiner;

/**
 * 操作日志记录处理
 *
 * @author zhangqingfu
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     * 线程本地变量,存储方法开始执行时间
     */
    private static final ThreadLocal<LocalDateTime> LOG_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 配置织入点
     */
    @Pointcut("@annotation(com.example.init.annotation.Log)")
    public void logPointCut() {
    }

    /**
     * 处理请求前执行
     *
     * @param joinPoint 切点
     */
    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        LocalDateTime beginTime = LocalDateTime.now();
        LOG_THREAD_LOCAL.set(beginTime);
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     * @param responseJson controller方法返回值
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "responseJson")
    public void doAfterReturning(JoinPoint joinPoint, Object responseJson) {
        LocalDateTime endTime = LocalDateTime.now();
        handleLog(joinPoint, null, responseJson, endTime);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e, null, null);
    }

    /**
     *
     * @param joinPoint 切点
     * @param e 异常
     * @param responseJson 方法返回值
     * @param endTime 结束时间
     */
    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object responseJson, LocalDateTime endTime) {
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            // 获得注解
            Log controllerLog = method.getAnnotation(Log.class);
            if (controllerLog == null) {
                return;
            }
            HttpServletRequest request = ServletUtils.getRequest();

            // 获取当前的用户
            //LoginUser loginUser = SpringUtils.getBean(TokenService.class).getLoginUser(ServletUtils.getRequest());

            // *========数据库日志=========*//
            SysLog sysLog = new SysLog();
            sysLog.setStatus(ApiErrorCode.SUCCESS.getCode());

            String ip = IpUtils.getIpAddr(request);
            sysLog.setIp(ip);
            sysLog.setLocation(AddressUtils.getRealAddressByIP(ip));
            sysLog.setRequestUrl(request.getRequestURI());
            // 请求的方法名
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = signature.getName();
            sysLog.setMethod(className + "." + methodName + "()");
            sysLog.setRequestType(request.getMethod());
            sysLog.setResponseJson(JSON.toJSONString(responseJson));
            // 有异常说明请求失败了
            if (e != null) {
                sysLog.setStatus(ApiErrorCode.FAILED.getCode());
                sysLog.setErrorMsg(e.getMessage());
            }
            LocalDateTime beginTime = LOG_THREAD_LOCAL.get();
            LOG_THREAD_LOCAL.remove();

            sysLog.setBeginTime(beginTime);
            if (!ObjectUtils.isEmpty(endTime)) {
                sysLog.setEndTime(endTime);
                sysLog.setCostTime(endTime.toInstant(ZoneOffset.of("+8")).toEpochMilli() - beginTime.toInstant(ZoneOffset.of("+8")).toEpochMilli());
            }

            // TODO: 2021/4/10 设置操作人
            // if (loginUser != null) {
            //     sysLog.setOperName(loginUser.getUsername());
            // }

            // 处理@Log注解上的参数
            getControllerMethodDescription(joinPoint, controllerLog, sysLog);
            log.info("sysLog:【{}】", sysLog);

            // TODO: 2021/4/11 日志入库
            // AsyncManager.me().execute(AsyncFactory.recordOper(sysLog));
        } catch (Exception exception) {
            log.error("controller层日志记录异常", exception);
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param log 日志
     * @param sysLog 操作日志
     * @throws Exception 异常
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, Log log, SysLog sysLog) throws Exception {
        sysLog.setModule(log.module());
        sysLog.setContent(log.content());
        sysLog.setBusinessType(log.businessType().getCode());
        // 是否需要保存请求的参数
        if (log.isSaveRequestData()) {
            setRequestValue(joinPoint, sysLog);
        }
    }

    /**
     * // TODO: 2021/4/10
     * 获取请求的参数，放到log中
     *
     * @param sysLog 操作日志
     */
    private void setRequestValue(JoinPoint joinPoint, SysLog sysLog) {
        String requestMethod = sysLog.getRequestType();

        if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
            // 获取post的json参数
            String params = argsArrayToString(joinPoint.getArgs());
            sysLog.setRequestParam(params);
        } else {
            StringJoiner params = new StringJoiner(",");
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            // 请求的方法参数值
            Object[] args = joinPoint.getArgs();
            // 请求的方法参数名称
            LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
            String[] paramNames = u.getParameterNames(method);
            if (args != null && paramNames != null) {
                for (int i = 0; i < args.length; i++) {
                    params.add(paramNames[i] + "=" + args[i]);
                }
            }
            if (StrUtil.isNotBlank(params.toString())) {
                sysLog.setRequestParam(params.toString());
            }
        }
    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray) {
        StringBuilder params = new StringBuilder();
        if (paramsArray != null && paramsArray.length > 0) {
            for (Object o : paramsArray) {
                if (!isFilterObject(o)) {
                    Object jsonObj = JSON.toJSON(o);
                    params.append(jsonObj.toString()).append(" ");
                }
            }
        }
        return params.toString().trim();
    }


    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    @SuppressWarnings("rawtypes")
    public boolean isFilterObject(final Object o) {
        Class<?> clazz = o.getClass();
        if (clazz.isArray()) {
            return clazz.getComponentType().isAssignableFrom(MultipartFile.class);
        } else if (Collection.class.isAssignableFrom(clazz)) {
            Collection collection = (Collection) o;
            for (Iterator iter = collection.iterator(); iter.hasNext(); ) {
                return iter.next() instanceof MultipartFile;
            }
        } else if (Map.class.isAssignableFrom(clazz)) {
            Map map = (Map) o;
            for (Iterator iter = map.entrySet().iterator(); iter.hasNext(); ) {
                Map.Entry entry = (Map.Entry) iter.next();
                return entry.getValue() instanceof MultipartFile;
            }
        }
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse;
    }

}
