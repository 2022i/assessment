package com.back.assessment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName score
 */
@TableName(value ="score")
@Data
public class Score implements Serializable {
    /**
     * 项目编号
     */
    private Integer id;

    /**
     * 指标得分
     */
    private Double tIndicatorScore;

    /**
     * 考核得分
     */
    private Double tAssessmentScore;

    /**
     * 乘以指标权重的考核得分
     */
    private Double tWeightedScore;

    /**
     * 缺陷率指标得分
     */
    private Double dIndicatorScore;

    /**
     * 缺陷关闭率指标得分
     */
    private Double dIndicatorScoreC;

    /**
     * 考核得分
     */
    private Double dAssessmentScore;

    /**
     * 乘以指标权重的考核得分
     */
    private Double dWeightedScore;

    /**
     * 指标得分
     */
    private Double uIndicatorScore;

    /**
     * 考核得分
     */
    private Double uAssessmentScore;

    /**
     * 乘以指标权重的得分
     */
    private Double uWeightedScore;

    /**
     * 
     */
    private Double totalScore;

    /**
     * 该指标占比编号
     */
    private Integer weightId;
}