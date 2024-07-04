package com.back.assessment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName project_time
 */
@TableName(value ="project_time")
@Data
public class ProjectTime implements Serializable {
    /**
     * 项目编号
     */
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

    /**
     * 计划开始时间
     */
    private Date pStart;

    /**
     * 计划结束时间
     */
    private Date pEnd;

    /**
     * 实际开始时间
     */
    private Date aStart;

    /**
     * 实际结束时间
     */
    private Date aEnd;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}