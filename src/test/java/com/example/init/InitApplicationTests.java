package com.example.init;

import cn.hutool.crypto.SecureUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InitApplicationTests {

    @Test
    void contextLoads() {

        String admin123 = SecureUtil.md5("admin123");
        System.out.println(admin123);
    }

}
