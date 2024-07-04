package com.back.assessment.controller;


import com.back.assessment.entity.Project;
import com.back.assessment.mapper.ProjectMapper;
import com.back.assessment.service.impl.ProjectServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    @Resource
    private ProjectServiceImpl projectService;
    @Resource
    private ProjectMapper projectMapper;

    @GetMapping("searchProjectById")
    public Project getProjectById(@RequestParam("projectId") int projectId) {
        return projectService.getProjectById(projectId);
    }

    //模糊查询方法一
//    @GetMapping( "/testTask")
//    public List<Project> fuzzy_search(@RequestParam("name") String name) {
//        return   projectService.fuzzy_search(name);
//    }

    @GetMapping("/fuzzySearchProjectByName")
    public List<Project> getProjectByName(@RequestParam("name") String name) {
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "%"+name+"%");
        List<Project> projectList = projectMapper.selectList(queryWrapper);
        return projectList;
    }

//    //Project的属性name的模糊查询
//    @GetMapping("/testTask")
//    public List<Project> fuzzy_search(@RequestParam("name") String name) {
//        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
//        queryWrapper.like("name", "%"+name+"%");
//        List<Project> projectList = projectMapper.selectList(queryWrapper);
//        return projectList;
//    }

}
