package com.example.init.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.init.vo.LoginBody;
import com.example.init.service.SysLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录
 *
 * @author 张庆福
 * @date 2021/4/10
 */
@Api(tags = "登录相关接口")
@RestController
public class SysLoginController {
    @Autowired
    private SysLoginService loginService;
    //
    // @Autowired
    // private ISysMenuService menuService;
    //
    // @Autowired
    // private SysPermissionService permissionService;
    //
    // @Autowired
    // private TokenService tokenService;

    @ApiOperation("登录方法")
    @PostMapping("/login")
    public R<String> login(@RequestBody LoginBody loginBody) {
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(), loginBody.getUuid());
        return R.ok(token);
    }


    /**
     * 用户退出
     */
/*    @GetMapping("/logout")
    public R<String> logout() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (StringUtils.isNotNull(loginUser)) {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Constants.LOGOUT, "退出成功"));
        }

        return R.ok("退出成功");
    }*/

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
/*    @GetMapping("getInfo")
    public AjaxResult getInfo() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }*/

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
/*    @GetMapping("getRouters")
    public AjaxResult getRouters() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
        return AjaxResult.success(menuService.buildMenus(menus));
    }*/
}
