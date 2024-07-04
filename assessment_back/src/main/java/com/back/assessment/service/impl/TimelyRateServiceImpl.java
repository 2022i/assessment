package com.back.assessment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.assessment.entity.TimelyRate;
import com.back.assessment.service.TimelyRateService;
import com.back.assessment.mapper.TimelyRateMapper;
import org.springframework.stereotype.Service;

/**
* @author 23831
* @description 针对表【timely_rate】的数据库操作Service实现
* @createDate 2024-07-02 21:22:53
*/
@Service
public class TimelyRateServiceImpl extends ServiceImpl<TimelyRateMapper, TimelyRate>
    implements TimelyRateService{
    @Override
    public void updateWeightId(Integer id, Integer weightId) {
        //修改指定id的TimelyRate实体的weightId字段
        TimelyRate timelyRate = this.getById(id);
        timelyRate.setWeightId(weightId);
        baseMapper.updateById(timelyRate);
    }

}




