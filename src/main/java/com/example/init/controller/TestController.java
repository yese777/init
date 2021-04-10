package com.example.init.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.init.annotation.Log;
import com.example.init.enums.BusinessType;
import com.example.init.vo.A;
import com.example.init.vo.B;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 测试
 *
 * @author 张庆福
 * @date 2021/4/7
 */
@Api(tags = "测试")
@ApiSupport(order = 284)
@RestController
@Slf4j
@Validated
public class TestController {

    @ApiImplicitParam(name = "name", value = "姓名", required = true)
    // 忽略表单参数(无@RequestBody):{"id","b.id"}
    // 忽略json参数(无@RequestBody):{"a.id", "a.b.id"}
    @ApiOperationSupport(author = "张庆福", order = 33, ignoreParameters = {"a.id", "a.b.id"})
    @ApiOperation(value = "向客人问好", notes = "这是描述")
    @GetMapping("/sayHi")
    public R<String> sayHi(@RequestParam String name, @RequestBody A a) {
        return R.ok("Hi:" + name);
    }

    @Log(module = "测试模块", content = "测试异常", businessType = BusinessType.OTHER)
    @ApiOperation(value = "test")
    @PostMapping("/test")
    public R<B> test(@RequestBody @Validated B b) {
        log.info("b:【{}】", b);
        return R.ok(b);
    }
}
