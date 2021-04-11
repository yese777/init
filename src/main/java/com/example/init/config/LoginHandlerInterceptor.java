package com.example.init.config;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.init.service.TokenService;
import com.example.init.utils.ResponseUtils;
import com.example.init.utils.SecurityUtils;
import com.example.init.utils.SpringUtils;
import com.example.init.vo.LoginUser;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 登录拦截器
 *
 * @author 张庆福
 * @date 2021/4/7
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {
    private TokenService tokenService = SpringUtils.getBean(TokenService.class);

    @Override
    // 目标方法执行之前
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (ObjectUtil.isNull(loginUser)) {
            R<?> failed = R.failed("未登录");
            ResponseUtils.writeAsJson(response, failed);
            // 未登录不放行
            return false;
        } else {
            tokenService.verifyToken(loginUser);
            // 登录，放行
            return true;
        }
    }

}
