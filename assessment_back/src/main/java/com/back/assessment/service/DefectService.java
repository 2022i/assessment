package com.back.assessment.service;

import com.back.assessment.entity.Defect;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 23831
* @description 针对表【defect】的数据库操作Service
* @createDate 2024-07-02 21:02:31
*/
public interface DefectService extends IService<Defect> {
    void changeWeightId(int projectId,int weightId);
}
