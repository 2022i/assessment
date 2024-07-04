package com.back.assessment.controller;

import com.back.assessment.dto.Request;
import com.back.assessment.service.impl.ChangeWeightServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/change")
public class ChangeWeightController {
    @Resource
    private ChangeWeightServiceImpl changeWeightService;

    @PostMapping("/changeWeight")
    public Request<String> changeWeight(@RequestParam int projectId, @RequestParam int weightId) {
        changeWeightService.changeWeight(projectId, weightId);
        return Request.success();
    }

}
