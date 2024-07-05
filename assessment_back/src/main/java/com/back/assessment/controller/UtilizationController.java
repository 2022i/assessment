package com.back.assessment.controller;


import com.back.assessment.entity.ProjectPerson;
import com.back.assessment.service.ProjectPersonService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utilization")
public class UtilizationController {

    @Resource
    private ProjectPersonService projectPersonService;

    @GetMapping("/getutilization")
    public ProjectPerson getProjectPersonById(@RequestParam int id) {
        return projectPersonService.getProjectPersonById(id);
    }
}
