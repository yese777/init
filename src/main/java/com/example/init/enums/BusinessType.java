package com.example.init.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务操作类型枚举
 *
 * @author zhangqingfu
 */
@Getter
@AllArgsConstructor
public enum BusinessType {
    /**
     * 其它
     */
    OTHER(0, "其它"),

    /**
     * 查询
     */
    SELECT(1, "查询"),

    /**
     * 修改
     */
    UPDATE(2, "修改"),

    /**
     * 删除
     */
    DELETE(3, "删除"),

    /**
     * 新增
     */
    INSERT(4, "新增"),

    /**
     * 导入
     */
    IMPORT(5, "导入"),

    /**
     * 导出
     */
    EXPORT(6, "导出"),

    /**
     * 登录
     */
    LOGIN(7, "登录"),

    /**
     * 注销
     */
    LOGOUT(8, "注销"),

    /**
     * 授权
     */
    GRANT(9, "授权"),

    ;


    /**
     * 响应码
     */
    private final int code;

    /**
     * 提示消息
     */
    private final String name;
}
