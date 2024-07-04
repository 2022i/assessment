package com.back.assessment.service;

import com.back.assessment.entity.TimelyRate;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 23831
* @description 针对表【timely_rate】的数据库操作Service
* @createDate 2024-07-02 21:22:53
*/
public interface TimelyRateService extends IService<TimelyRate> {
    //修改指定编号的TimelyRate实体的wightId字段
    void updateWeightId(Integer id, Integer wightId);
    TimelyRate getTimelyRateById(Integer id);
}
