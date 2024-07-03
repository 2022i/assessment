package com.back.assessment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName timely_rate
 */
@TableName(value ="timely_rate")
@Data
public class TimelyRate implements Serializable {
    /**
     * 项目编号
     */
    @TableId
    private Integer id;

    /**
     * 及时率
     */
    private Double timelinessRate;

    /**
     * 指标得分
     */
    private Double indicatorScore;

    /**
     * 考核得分
     */
    private Double assessmentScore;

    /**
     * 乘以指标权重的考核得分
     */
    private Double weightedScore;

    /**
     * 该指标占比编号
     */
    private Integer weightId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}