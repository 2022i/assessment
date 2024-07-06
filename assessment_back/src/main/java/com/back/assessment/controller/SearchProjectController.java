package com.back.assessment.controller;

import com.back.assessment.dto.Request;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.back.assessment.entity.Project;
import com.back.assessment.mapper.ProjectMapper;
import com.back.assessment.service.impl.ProjectServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8081")
@RestController
@RequestMapping("/search")
public class SearchProjectController {
    @Resource
    private ProjectServiceImpl projectService;
    @Resource
    private ProjectMapper projectMapper;

    @GetMapping("searchProjectById")
    public Project getProjectById(@RequestParam("projectId") int projectId) {
        Project project = projectService.getProjectById(projectId);
        if(project == null) {
           return Request.notFoundProjectId(projectId);
        }
        return project;
    }




    @GetMapping("/fuzzySearchProjectByName")
    public List<Project> getProjectByName(@RequestParam("name") String name) {
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "%"+name+"%");
        List<Project> projectList = projectMapper.selectList(queryWrapper);
        if(projectList.isEmpty()){
         return Request.notFoundProject();
        }
        return projectList;
    }



    @GetMapping("/searchProjectByPage")
    public Page<Project> getProjectByPage(@RequestParam("page") int page, @RequestParam("size") int size) {
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        Page<Project> projectPage = new Page<>(page, size);
//        queryWrapper.like("name", "%"+name+"%");
        projectPage = projectMapper.selectPage(projectPage, queryWrapper);
        return projectPage;
    }

    @PostMapping("/getPageCount")
    public int getPageByID(@RequestParam("size") int size) {
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        // 获取总数据量
        int totalRecords = Math.toIntExact(projectMapper.selectCount(queryWrapper));
        // 获取总页数
        return (int) Math.ceil((double) totalRecords / size);
    }




}
