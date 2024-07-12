package com.back.assessment.controller;

import com.back.assessment.dto.Response;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.back.assessment.entity.Project;
import com.back.assessment.mapper.ProjectMapper;
import com.back.assessment.service.impl.ProjectServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import jdk.jfr.Description;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/search")
public class SearchProjectController {
    @Resource
    private ProjectServiceImpl projectService;
    @Resource
    private ProjectMapper projectMapper;

    @Description("根据项目ID搜索项目")
    @GetMapping("searchProjectById")
    public Project getProjectById(@RequestParam("projectId") int projectId) {
        Project project = projectService.getProjectById(projectId);
        if(project == null) {
           return Response.notFoundProjectId(projectId);
        }
        return project;
    }

    @Description("根据项目名称模糊搜索项目")
    @GetMapping("/fuzzySearchProjectByName")
    public List<Project> getProjectByName(@RequestParam("name") String name) {
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "%"+name+"%");
        List<Project> projectList = projectMapper.selectList(queryWrapper);
        if(projectList.isEmpty()){
         return Response.notFoundProject();
        }
        return projectList;
    }

    @Description("根据页面大小和页面编号呈现一页数量的项目")
    @GetMapping("/searchProjectByPage")
    public Page<Project> getProjectByPage(@RequestParam("page") int page, @RequestParam("size") int size) {
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        Page<Project> projectPage = new Page<>(page, size);
//        queryWrapper.like("name", "%"+name+"%");
        projectPage = projectMapper.selectPage(projectPage, queryWrapper);
        return projectPage;
    }

    @Description("根据页面大小获取总页数")
    @PostMapping("/getPageCount")
    public int getPageByID(@RequestParam("size") int size) {
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        // 获取总数据量
        int totalRecords = Math.toIntExact(projectMapper.selectCount(queryWrapper));
        // 获取总页数
        return (int) Math.ceil((double) totalRecords / size);
    }

    @Description("获取所有项目")
    @GetMapping("/getAllProjects")
    @CrossOrigin(origins = "http://localhost:8080")
    public List<Project> getAllProjects() {
        return projectService.getAllProjects();
    }
}
