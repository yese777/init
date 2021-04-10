package com.example.init.vo;

import com.example.init.entity.SysUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * 登录用户身份权限
 */
@ApiModel(description = "登录用户身份权限")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户唯一标识")
    private String token;

    @ApiModelProperty("登录时间")
    private Long loginTime;

    @ApiModelProperty("过期时间")
    private Long expireTime;

    @ApiModelProperty("登录IP地址")
    private String ipaddr;

    @ApiModelProperty("登录地点")
    private String loginLocation;

    @ApiModelProperty("浏览器类型")
    private String browser;

    @ApiModelProperty("操作系统")
    private String os;

    @ApiModelProperty("权限列表")
    private Set<String> permissions;

    @ApiModelProperty("用户信息")
    private SysUser user;

}
