package com.back.assessment.controller;

import com.back.assessment.entity.ProjectTime;
import com.back.assessment.service.ProjectTimeService;
import jakarta.annotation.Resource;
import jdk.jfr.Description;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/projectTime")
public class ProjectTimeController {
    @Resource
    private ProjectTimeService projectTimeService;


    @Description("根据项目ID给出对应项目及时率表的所有信息")
    @GetMapping("/getProjectTimeById")
    public ProjectTime getProjectTimeById(@RequestParam("id") int id) {
        return projectTimeService.getProjectTimeById(id);
    }

}
