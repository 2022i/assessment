package com.back.assessment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName defect
 */
@TableName(value ="defect")
@Data
public class Defect implements Serializable {
    /**
     * 项目编号
     */
    @TableId
    private Integer id;

    /**
     * 缺陷率
     */
    private Double defectRate;

    /**
     * 缺陷率指标得分
     */
    private Double indicatorScore;

    /**
     * 缺陷关闭率
     */
    private Double defectCloseRate;

    /**
     * 缺陷关闭率指标得分
     */
    private Double indicatorScoreC;

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