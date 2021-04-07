package com.example.init.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.init.exception.BusinessException;
import com.example.init.vo.A;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 *
 * @author 张庆福
 * @date 2021/4/7
 */
@Api(tags = "测试")
@ApiSupport(order = 284)
@RestController
public class TestController {

    @ApiImplicitParam(name = "name", value = "姓名", required = true)
    // 忽略表单参数(无@RequestBody):{"id","b.id"}
    // 忽略json参数(无@RequestBody):{"a.id", "a.b.id"}
    @ApiOperationSupport(author = "张庆福", order = 33, ignoreParameters = {"a.id", "a.b.id"})
    @ApiOperation(value = "向客人问好")
    @GetMapping("/sayHi")
    public R<String> sayHi(@RequestParam String name, @RequestBody A a) {
        return R.ok("Hi:" + name);
    }

    @ApiOperation(value = "test")
    @GetMapping("/test")
    public R<String> test() {

        if (1 == 1) {

            throw new BusinessException("我错了");
        }
        return R.ok("1");
    }
}
