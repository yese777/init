package com.example.init.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取项目相关配置
 *
 * @author zhangqingfu
 */
@Data
@Component
@ConfigurationProperties(prefix = "system")
public class SystemConfig {
    /**
     * 项目名称
     */
    private String name;

    /**
     * 开发者
     */
    private String developer;

    /**
     * 版本
     */
    private String version;

}
