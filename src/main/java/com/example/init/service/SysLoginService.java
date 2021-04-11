package com.example.init.service;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.init.entity.SysUser;
import com.example.init.enums.UserStatusEnum;
import com.example.init.exception.BusinessException;
import com.example.init.utils.RedisCache;
import com.example.init.utils.SecurityUtils;
import com.example.init.vo.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 登录校验方法
 */
@Component
@Slf4j
public class SysLoginService {
    @Autowired
    private TokenService tokenService;

    // @Autowired
    // private ISysUserService userService;
    //
    // @Autowired
    // private SysPermissionService permissionService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid) {
        // TODO: 2021/4/11  根据用户名查找用户
        // SysUser sysUser = userService.selectUserByUserName(username);

        SysUser sysUser = new SysUser();
        sysUser.setUserName("admin");
        sysUser.setPassword(SecureUtil.md5("123456"));

        // 2021/4/6  登录验证去除验证码校验
        // String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        // String captcha = redisCache.getCacheObject(verifyKey);
        // redisCache.deleteObject(verifyKey);
        // if (captcha == null)
        // {
        //     AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
        //     throw new CaptchaExpireException();
        // }
        // if (!code.equalsIgnoreCase(captcha))
        // {
        //     AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
        //     throw new CaptchaException();
        // }

        // 用户验证
        if (ObjectUtil.isNull(sysUser)) {
            log.info("登录用户：{} 不存在.", username);
            // 用户不存在
            // AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.not.exists")));
            throw new BusinessException("登录用户：" + username + " 不存在");
        } else if (UserStatusEnum.DELETED.getCode().equals(sysUser.getDelFlag())) {
            log.info("登录用户：{} 已被删除.", username);
            // AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, "用户已被删除"));
            throw new BusinessException("对不起，您的账号：" + username + " 已被删除");
        } else if (UserStatusEnum.DISABLE.getCode().equals(sysUser.getStatus())) {
            log.info("登录用户：{} 已被停用.", username);
            // AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, "用户已停用"));
            throw new BusinessException("对不起，您的账号：" + username + " 已停用");
        } else if (!SecurityUtils.matchesPassword(password, sysUser.getPassword())) {
            // 密码错误
            log.info("登录用户：{} 密码错误.", username);
            // AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
            throw new BusinessException("用户不存在/密码错误");
        }
        // AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));

        LoginUser loginUser = new LoginUser();
        loginUser.setUser(sysUser);
        // 生成token
        return tokenService.createToken(loginUser);
    }
}