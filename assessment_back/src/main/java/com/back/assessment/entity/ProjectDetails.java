package com.back.assessment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName project_details
 */
@TableName(value ="project_details")
@Data
public class ProjectDetails implements Serializable {
    /**
     * 项目编号
     */@TableId
    private Integer id;

    /**
     * 人员成本使用率
     */
    private Double usageRate;

    /**
     * 指标得分
     */
    private Double iSU;

    /**
     * 考核得分
     */
    private Double aSU;

    /**
     * 缺陷率
     */
    private Double defectRate;

    /**
     * 缺陷率指标得分
     */
    private Double iSD;

    /**
     * 考核得分
     */
    private Double aSD;

    /**
     * 缺陷关闭率
     */
    private Double defectCloseRate;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目分类
     */
    private String typesSection;

    /**
     * 项目类型
     */
    private String projectType;

    /**
     * 项目所用权重编号
     */
    private Integer wP;

    /**
     * 及时率
     */
    private Double timelinessRate;

    /**
     * 指标得分
     */
    private Double iST;

    /**
     * 考核得分
     */
    private Double aST;

    /**
     * 该指标占比编号
     */
    private Integer wD;

    /**
     * 该指标占比编号
     */
    private Integer wU;

    /**
     * 该指标占比编号
     */
    private Integer wT;

    /**
     * 
     */
    private Double totalScore;

    /**
     * 乘以指标权重的考核得分
     */
    private Double wST;

    /**
     * 乘以指标权重的得分
     */
    private Double wSU;

    /**
     * 乘以指标权重的考核得分
     */
    private Double wSD;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}