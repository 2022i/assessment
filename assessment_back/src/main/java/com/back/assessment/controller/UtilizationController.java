package com.back.assessment.controller;


import com.back.assessment.entity.ProjectPerson;
import com.back.assessment.service.ProjectPersonService;
import jakarta.annotation.Resource;
import jdk.jfr.Description;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/utilization")
public class UtilizationController {

    @Resource
    private ProjectPersonService projectPersonService;

    @Description("根据项目ID给出对应项目人员成本使用率表的所有信息")
    @GetMapping("/getUtilization")
    public ProjectPerson getProjectPersonById(@RequestParam int id) {
        return projectPersonService.getProjectPersonById(id);
    }
}
