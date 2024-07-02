package com.back.assessment.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName view_score
 */
@TableName(value ="view_score")
@Data
public class ViewScore implements Serializable {
    /**
     * 项目编号
     */
    private Integer id;

    /**
     * 该指标占比
     */
    private Double timelyRateWeight;

    /**
     * 指标得分
     */
    private Double timelyRateIndicatorScore;

    /**
     * 考核得分
     */
    private Double timelyRateAssessmentScore;

    /**
     * 该指标权重
     */
    private Double defectWeight;

    /**
     * 缺陷率指标得分
     */
    private Double defectIndicatorScore;

    /**
     * 缺陷关闭率指标得分
     */
    private Double defectIndicatorScoreC;

    /**
     * 考核得分
     */
    private Double defectAssessmentScore;

    /**
     * 该指标占比
     */
    private Double usageWeight;

    /**
     * 指标得分
     */
    private Double usageIndicatorScore;

    /**
     * 考核得分
     */
    private Double usageAssessmentScore;

    /**
     * 
     */
    private Double totalScore;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ViewScore other = (ViewScore) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTimelyRateWeight() == null ? other.getTimelyRateWeight() == null : this.getTimelyRateWeight().equals(other.getTimelyRateWeight()))
            && (this.getTimelyRateIndicatorScore() == null ? other.getTimelyRateIndicatorScore() == null : this.getTimelyRateIndicatorScore().equals(other.getTimelyRateIndicatorScore()))
            && (this.getTimelyRateAssessmentScore() == null ? other.getTimelyRateAssessmentScore() == null : this.getTimelyRateAssessmentScore().equals(other.getTimelyRateAssessmentScore()))
            && (this.getDefectWeight() == null ? other.getDefectWeight() == null : this.getDefectWeight().equals(other.getDefectWeight()))
            && (this.getDefectIndicatorScore() == null ? other.getDefectIndicatorScore() == null : this.getDefectIndicatorScore().equals(other.getDefectIndicatorScore()))
            && (this.getDefectIndicatorScoreC() == null ? other.getDefectIndicatorScoreC() == null : this.getDefectIndicatorScoreC().equals(other.getDefectIndicatorScoreC()))
            && (this.getDefectAssessmentScore() == null ? other.getDefectAssessmentScore() == null : this.getDefectAssessmentScore().equals(other.getDefectAssessmentScore()))
            && (this.getUsageWeight() == null ? other.getUsageWeight() == null : this.getUsageWeight().equals(other.getUsageWeight()))
            && (this.getUsageIndicatorScore() == null ? other.getUsageIndicatorScore() == null : this.getUsageIndicatorScore().equals(other.getUsageIndicatorScore()))
            && (this.getUsageAssessmentScore() == null ? other.getUsageAssessmentScore() == null : this.getUsageAssessmentScore().equals(other.getUsageAssessmentScore()))
            && (this.getTotalScore() == null ? other.getTotalScore() == null : this.getTotalScore().equals(other.getTotalScore()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTimelyRateWeight() == null) ? 0 : getTimelyRateWeight().hashCode());
        result = prime * result + ((getTimelyRateIndicatorScore() == null) ? 0 : getTimelyRateIndicatorScore().hashCode());
        result = prime * result + ((getTimelyRateAssessmentScore() == null) ? 0 : getTimelyRateAssessmentScore().hashCode());
        result = prime * result + ((getDefectWeight() == null) ? 0 : getDefectWeight().hashCode());
        result = prime * result + ((getDefectIndicatorScore() == null) ? 0 : getDefectIndicatorScore().hashCode());
        result = prime * result + ((getDefectIndicatorScoreC() == null) ? 0 : getDefectIndicatorScoreC().hashCode());
        result = prime * result + ((getDefectAssessmentScore() == null) ? 0 : getDefectAssessmentScore().hashCode());
        result = prime * result + ((getUsageWeight() == null) ? 0 : getUsageWeight().hashCode());
        result = prime * result + ((getUsageIndicatorScore() == null) ? 0 : getUsageIndicatorScore().hashCode());
        result = prime * result + ((getUsageAssessmentScore() == null) ? 0 : getUsageAssessmentScore().hashCode());
        result = prime * result + ((getTotalScore() == null) ? 0 : getTotalScore().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", timelyRateWeight=").append(timelyRateWeight);
        sb.append(", timelyRateIndicatorScore=").append(timelyRateIndicatorScore);
        sb.append(", timelyRateAssessmentScore=").append(timelyRateAssessmentScore);
        sb.append(", defectWeight=").append(defectWeight);
        sb.append(", defectIndicatorScore=").append(defectIndicatorScore);
        sb.append(", defectIndicatorScoreC=").append(defectIndicatorScoreC);
        sb.append(", defectAssessmentScore=").append(defectAssessmentScore);
        sb.append(", usageWeight=").append(usageWeight);
        sb.append(", usageIndicatorScore=").append(usageIndicatorScore);
        sb.append(", usageAssessmentScore=").append(usageAssessmentScore);
        sb.append(", totalScore=").append(totalScore);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}