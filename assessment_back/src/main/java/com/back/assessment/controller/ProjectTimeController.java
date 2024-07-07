package com.back.assessment.controller;

import com.back.assessment.entity.ProjectTime;
import com.back.assessment.service.ProjectTimeService;
import jakarta.annotation.Resource;
import jdk.jfr.Description;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/projectTime")
public class ProjectTimeController {
    @Resource
    private ProjectTimeService projectTimeService;


    @Description("根据项目ID给出对应项目及时率表的所有信息")
    @GetMapping("/getProjectTimeById")
    public ProjectTime getProjectTimeById(int id) {
        return projectTimeService.getProjectTimeById(id);
    }

}
