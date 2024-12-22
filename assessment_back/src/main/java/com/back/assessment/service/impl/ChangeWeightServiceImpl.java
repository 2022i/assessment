package com.back.assessment.service.impl;

import com.back.assessment.service.ChangeWeightService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ChangeWeightServiceImpl implements ChangeWeightService {
    @Resource
    private ProjectServiceImpl projectService;

    @Resource
    private TimelyRateServiceImpl timelyRateService;

    @Resource
    private DefectServiceImpl defectService;

    @Resource
    private UtilizationServiceImpl utilizationService;

    @Override
    public void changeWeight(int id, int weightId) {
        // TODO Auto-generated method stub
        projectService.changeProjectWeight(id,weightId);
        timelyRateService.updateWeightId(id,weightId);
        defectService.changeWeightId(id,weightId);
        utilizationService.changeWeightId(id,weightId);
    }
}
