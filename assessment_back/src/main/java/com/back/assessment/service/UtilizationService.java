package com.back.assessment.service;

import com.back.assessment.entity.Utilization;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 23831
* @description 针对表【utilization】的数据库操作Service
* @createDate 2024-07-03 09:32:46
*/
public interface UtilizationService extends IService<Utilization> {
    void changeWeightId(Integer id, Integer weightId);
    Utilization getUtilizationById(Integer id);
}
