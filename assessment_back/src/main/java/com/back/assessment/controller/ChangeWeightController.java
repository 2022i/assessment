package com.back.assessment.controller;

import com.back.assessment.dto.Response;
import com.back.assessment.service.impl.ChangeWeightServiceImpl;
import jakarta.annotation.Resource;
import jdk.jfr.Description;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/change")
public class ChangeWeightController {
    @Resource
    private ChangeWeightServiceImpl changeWeightService;

    @Description("修改项目表、及时率表、缺陷率表、人员成本使用率表权重")
    @PostMapping("/changeWeight")
    public Response<String> changeWeight(@RequestParam int projectId, @RequestParam int weightId) {
        changeWeightService.changeWeight(projectId, weightId);
        return Response.success();
    }

}
