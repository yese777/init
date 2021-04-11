package com.example.init.utils;

import cn.hutool.crypto.SecureUtil;
import com.example.init.exception.BusinessException;
import com.example.init.service.TokenService;
import com.example.init.vo.LoginUser;

/**
 * 安全服务工具类
 *
 * @author ruoyi
 */
public class SecurityUtils {
    private static TokenService tokenService = SpringUtils.getBean(TokenService.class);

    /**
     * 获取用户账户
     **/
    public static String getUsername() {
        try {
            return getLoginUser().getUser().getUserName();
        } catch (Exception e) {
            throw new BusinessException("获取用户账户异常");
        }
    }

    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser() {
        try {
            return tokenService.getLoginUser(ServletUtils.getRequest());
        } catch (Exception e) {
            throw new BusinessException("获取用户信息异常");
        }
    }


    /**
     * 生成密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        return SecureUtil.md5(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword 真实密码
     * @param encodedPassword 加密后字符
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword) {
        return encodedPassword.equals(SecureUtil.md5(rawPassword));
    }

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }
}
