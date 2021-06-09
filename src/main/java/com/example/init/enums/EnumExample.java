package com.example.init.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum EnumExample {
    OK(0, "正常"), DISABLE(1, "停用"), DELETED(2, "删除");

    private final Integer code;
    private final String msg;

    public static EnumExample getEnumByCode(Integer code) {
        for (EnumExample value : EnumExample.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        throw new RuntimeException("枚举类型不存在，code:" + code);
    }
}
