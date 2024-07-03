package com.back.assessment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName utilization
 */
@TableName(value ="utilization")
@Data
public class Utilization implements Serializable {
    /**
     * 项目编号
     */
    @TableId
    private Integer id;

    /**
     * 人员成本使用率
     */
    private Double usageRate;

    /**
     * 指标得分
     */
    private Double indicatorScore;

    /**
     * 考核得分
     */
    private Double assessmentScore;

    /**
     * 乘以指标权重的得分
     */
    private Double weightedScore;

    /**
     * 该指标占比编号
     */
    private Integer weightId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}