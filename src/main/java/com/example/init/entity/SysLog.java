package com.example.init.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 系统日志表
 *
 * @author zhangqingfu
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 操作人id
     */
    private Long userId;

    /**
     * 操作人用户名称
     */
    private String username;

    /**
     * 操作模块
     */
    private String module;

    /**
     * 操作详细日志
     */
    private String content;

    /**
     * 业务操作类型,0=其它,1=查询,2=修改,3=删除,4=新增,5=导入,6=导出,7=登录,8=退出,9=授权
     */
    private Integer businessType;

    /**
     * 操作结果.0==成功,-1==失败
     */
    private Long status;

    /**
     * IP
     */
    private String ip;

    /**
     * 操作地点
     */
    private String location;

    /**
     * 请求路径
     */
    private String requestUrl;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 请求类型
     */
    private String requestType;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 返回json
     */
    private String responseJson;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 请求开始时间
     */
    private LocalDateTime beginTime;

    /**
     * 请求结束时间
     */
    private LocalDateTime endTime;

    /**
     * 耗时,单位:毫秒
     */
    private Long costTime;

}