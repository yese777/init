package com.example.init.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 角色表
 */
@ApiModel(description = "角色表")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysRole {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty("角色ID")
    private Long roleId;

    @ApiModelProperty("角色名称")
    @NotBlank(message = "角色名称不能为空")
    @Size(min = 0, max = 30, message = "角色名称长度不能超过30个字符")
    private String roleName;

    @ApiModelProperty("角色权限")
    @NotBlank(message = "权限字符不能为空")
    @Size(min = 0, max = 100, message = "权限字符长度不能超过100个字符")
    private String roleKey;

    @ApiModelProperty("角色排序")
    @NotBlank(message = "显示顺序不能为空")
    private String roleSort;

    @ApiModelProperty("数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限）")
    private String dataScope;

    @ApiModelProperty("菜单树选择项是否关联显示（ 0：父子不互相关联显示 1：父子互相关联显示）")
    private boolean menuCheckStrictly;

    @ApiModelProperty("部门树选择项是否关联显示（0：父子不互相关联显示 1：父子互相关联显示 ）")
    private boolean deptCheckStrictly;

    @ApiModelProperty("角色状态（0正常 1停用）")
    private String status;

    @ApiModelProperty("删除标志（0代表存在 2代表删除）")
    private String delFlag;

    @ApiModelProperty("用户是否存在此角色标识 默认不存在")
    private boolean flag = false;

    @ApiModelProperty("菜单组")
    private Long[] menuIds;

    @ApiModelProperty("部门组（数据权限）")
    private Long[] deptIds;

}
