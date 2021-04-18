package com.example.init.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路径
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOrigins("*")
                // 是否允许证书 不再默认开启
                .allowCredentials(true)
                // 设置允许的方法
                .allowedMethods("*")
                // 允许的请求头
                .allowedHeaders("*")
                // 跨域允许时间
                .maxAge(3600);
    }

    // 注册登录拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 注册登录拦截器
         * addPathPatterns:/**:拦截所有请求
         * excludePathPatterns:排除哪些请求(登录不能拦截;静态资源文件不能拦截，否则样式显示不出来)
         *
         */

        // 不拦截的路径
        List<String> excludePath = new ArrayList<>();
        excludePath.add("/login");
        excludePath.add("/asserts/**");
        excludePath.add("/webjars/**");
        excludePath.add("/doc.html");
        excludePath.add("/swagger-ui.html");
        excludePath.add("/profile/**");
        excludePath.add("/common/download**");
        excludePath.add("/common/download/resource**");
        excludePath.add("/swagger-ui.html");
        excludePath.add("/swagger-resources/**");
        excludePath.add("/webjars/**");
        excludePath.add("/*/api-docs");
        excludePath.add("/druid/**");
        excludePath.add("/error");

        // websocket页面
        excludePath.add("/client.html");

        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**").excludePathPatterns(excludePath);
    }

}