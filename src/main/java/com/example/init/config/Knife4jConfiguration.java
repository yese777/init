package com.example.init.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * Knife4j配置类
 *
 * @author 张庆福
 * @date 2021/4/7
 */
@Configuration
@EnableSwagger2WebMvc
@Profile({"dev", "test"})
public class Knife4jConfiguration {
    @Autowired
    private SystemConfig systemConfig;

    @Bean(value = "defaultApi")
    public Docket defaultApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title(systemConfig.getName())
                        .description("描述")
                        .termsOfServiceUrl("服务条款URL")
                        .contact(new Contact(systemConfig.getDeveloper(), "主页", "邮箱"))
                        .version(systemConfig.getVersion())
                        .build())
                //分组名称
                .groupName("组1")
                // 设置哪些接口暴露给Swagger展示
                .select()
                // 扫描指定包中的swagger注解
                .apis(RequestHandlerSelectors.basePackage("com.example.init.controller"))

                // 扫描所有有注解的api，用这种方式更灵活
                // .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 扫描所有 .apis(RequestHandlerSelectors.any())

                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
