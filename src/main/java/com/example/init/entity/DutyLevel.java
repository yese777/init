package com.example.init.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


/**
 * 勤务等级表实体类
 *
 * @author 张庆福
 * @since 2021-04-14 00:38:25
 */
@ApiModel("勤务等级表")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("duty_level")
public class DutyLevel implements Serializable {

    private static final long serialVersionUID = 586566598657255999L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 派出所名称
     */
    @ApiModelProperty(value = "派出所名称")
    private String policeStationName;

    /**
     * 等级
     */
    @ApiModelProperty(value = "等级")
    private String level;

    /**
     * 删除标志:0=未删除,1=已删除
     */
    @ApiModelProperty(value = "删除标志:0=未删除,1=已删除")
    private Integer del;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
