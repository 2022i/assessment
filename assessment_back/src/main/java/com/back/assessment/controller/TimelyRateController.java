package com.back.assessment.controller;


import com.back.assessment.entity.TimelyRate;
import com.back.assessment.service.TimelyRateService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/timelyRate")
public class TimelyRateController {
    @Resource
    private TimelyRateService timelyRateService;


    @GetMapping("/updateWeightId")
    public TimelyRate getTimelyRateById(@RequestParam("id") Integer id) {
        return timelyRateService.getTimelyRateById(id);
    }
}
