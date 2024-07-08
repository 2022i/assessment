package com.back.assessment.controller;

import com.back.assessment.entity.ProjectDetails;
import com.back.assessment.service.ProjectDetailsService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projectDetails")
@CrossOrigin("*")
public class ProjectDetailsController {
    @Resource
    private ProjectDetailsService projectDetailsService;
    @GetMapping("/getProjectDetails")
    public ProjectDetails getProjectDetails(@RequestParam("id") int projectId) {
        return projectDetailsService.getProjectDetails(projectId);
    }
}