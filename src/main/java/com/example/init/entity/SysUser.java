package com.example.init.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户对象
 */
@ApiModel(description = "用户对象")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("部门ID")
    private Long deptId;

    @ApiModelProperty("用户账号")
    @NotBlank(message = "用户账号不能为空")
    @Size(min = 0, max = 30, message = "用户账号长度不能超过30个字符")
    private String userName;

    @ApiModelProperty("用户昵称")
    @Size(min = 0, max = 30, message = "用户昵称长度不能超过30个字符")
    private String nickName;

    @ApiModelProperty("用户邮箱")
    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    private String email;

    @ApiModelProperty("手机号码")
    @Size(min = 0, max = 11, message = "手机号码长度不能超过11个字符")
    private String phonenumber;

    @ApiModelProperty("用户性别,0=男,1=女,2=未知")
    private String sex;

    @ApiModelProperty("用户头像")
    private String avatar;

    @ApiModelProperty("密码")
    @JsonIgnore
    @JsonProperty
    private String password;

    @ApiModelProperty("盐加密")
    private String salt;

    @ApiModelProperty("帐号状态(0正常 1停用)")
    private String status;

    @ApiModelProperty("删除标志(0代表存在 2代表删除)")
    private String delFlag;

    @ApiModelProperty("最后登录IP")
    private String loginIp;

    @ApiModelProperty("最后登录时间")
    private Date loginDate;

    @ApiModelProperty("部门对象")
    private SysDept dept;

    @ApiModelProperty("角色对象")
    private List<SysRole> roles;

    @ApiModelProperty("角色组")
    private Long[] roleIds;

    @ApiModelProperty("岗位组")
    private Long[] postIds;

}
