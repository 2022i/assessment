package com.back.assessment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.assessment.entity.Utilization;
import com.back.assessment.service.UtilizationService;
import com.back.assessment.mapper.UtilizationMapper;
import org.springframework.stereotype.Service;

/**
* @author 23831
* @description 针对表【utilization】的数据库操作Service实现
* @createDate 2024-07-03 09:32:46
*/
@Service
public class UtilizationServiceImpl extends ServiceImpl<UtilizationMapper, Utilization>
    implements UtilizationService{
    @Override
    public void changeWeightId(Integer id, Integer weightId) {
        // 根据指定的id更新Utilization的weightId属性
        Utilization utilization = this.getById(id);
        utilization.setWeightId(weightId);
        baseMapper.updateById(utilization);
    }
}




