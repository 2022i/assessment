package com.back.assessment.controller;


import com.back.assessment.entity.Utilization;
import com.back.assessment.service.UtilizationService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utilization")
public class UtilizationController {
    @Resource
    private UtilizationService utilizationService;

    @GetMapping("/getutilization")
    public Utilization getUtilization(@RequestParam int utilizationId) {
        return utilizationService.getUtilizationById(utilizationId);
    }
}
