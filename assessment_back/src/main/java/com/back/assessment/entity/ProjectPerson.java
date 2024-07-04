package com.back.assessment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName project_person
 */
@TableName(value ="project_person")
@Data
public class ProjectPerson implements Serializable {
    /**
     * 项目编号
     */
    private Integer id;

    /**
     * 计划人员成本
     */
    private Double pStaff;

    /**
     * 实际人员成本
     */
    private Double aStaff;

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