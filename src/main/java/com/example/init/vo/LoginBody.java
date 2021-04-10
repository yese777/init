package com.example.init.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户登录对象
 *
 */
@ApiModel(description = "用户登录对象")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginBody {
    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("验证码")
    private String code;

    @ApiModelProperty("唯一标识")
    private String uuid = "";

}
