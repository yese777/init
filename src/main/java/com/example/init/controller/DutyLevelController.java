package com.example.init.controller;

import com.example.init.entity.DutyLevel;
import com.example.init.service.DutyLevelService;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * 勤务等级表控制层
 *
 * @author 张庆福
 * @since 2021-04-14 00:38:25
 */
@Api(tags = "勤务等级表接口")
@Slf4j
@RestController
@RequestMapping("dutyLevel")
public class DutyLevelController {

    /**
     * 服务对象
     */
    @Autowired
    private DutyLevelService dutyLevelService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param dutyLevel 查询实体
     * @return 分页数据
     */
    @ApiOperation("分页查询所有数据")
    @GetMapping
    public R<Page<DutyLevel>> selectAll(Page<DutyLevel> page, DutyLevel dutyLevel) {
        return R.ok(dutyLevelService.page(page, new QueryWrapper<>(dutyLevel)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("通过主键查询单条数据")
    @GetMapping("{id}")
    public R<DutyLevel> selectOne(@PathVariable Serializable id) {
        return R.ok(dutyLevelService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param dutyLevel 实体对象
     * @return id
     */
    @ApiOperation("新增数据")
    @PostMapping
    public R<Long> insert(@RequestBody DutyLevel dutyLevel) {
        boolean flag = dutyLevelService.save(dutyLevel);
        if (flag) {
            return R.ok(dutyLevel.getId());
        }
        return R.failed("新增失败");
    }

    /**
     * 根据id修改数据
     *
     * @param dutyLevel 实体对象
     * @return id
     */
    @ApiOperation("根据id修改数据")
    @PutMapping
    public R<?> update(@RequestBody DutyLevel dutyLevel) {
        boolean flag = dutyLevelService.updateById(dutyLevel);
        if (flag) {
            return R.ok(dutyLevel.getId());
        }
        return R.failed("修改失败");
    }

    /**
     * 单条/批量删除数据
     *
     * @param ids 主键集合
     */
    @ApiOperation("单条/批量删除数据")
    @DeleteMapping
    public R<?> delete(@RequestParam List<Long> ids) {
        boolean flag = dutyLevelService.removeByIds(ids);
        if (flag) {
            return R.ok(null);
        }
        return R.failed("删除失败");
    }
}


