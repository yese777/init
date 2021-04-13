package com.example.init;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.init.mapper")
public class InitApplication {

    public static void main(String[] args) {
        SpringApplication.run(InitApplication.class, args);
    }

}
