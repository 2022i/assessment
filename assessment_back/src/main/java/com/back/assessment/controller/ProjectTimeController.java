package com.back.assessment.controller;

import com.back.assessment.entity.ProjectTime;
import com.back.assessment.service.ProjectTimeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projectTime")
public class ProjectTimeController {
    @Resource
    private ProjectTimeService projectTimeService;


    @GetMapping("/getProjectTimeById")
    public ProjectTime getProjectTimeById(int id) {
        return projectTimeService.getProjectTimeById(id);
    }

}
