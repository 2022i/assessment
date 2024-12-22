package com.back.assessment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.assessment.entity.Project;
import com.back.assessment.service.ProjectService;
import com.back.assessment.mapper.ProjectMapper;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @author dell
*/
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService{

    @Resource
    private ProjectMapper projectMapper;

    @Override
    public Project getProjectById(int id) {
        return baseMapper.selectById(id);
    }

    @Override
    public List<Project> getProjectsByName(String name) {
        return List.of();
    }

   @Override
    public void changeProjectWeight(int projectId, int weightId) {
        //获取指定项目的Project实体，根据weightId改变其weightId属性
        Project project = getProjectById(projectId);
        project.setWeightId(weightId);
        //更新数据库
        baseMapper.updateById(project);
    }

    @Override
    public Page<Project> getProjectByPage(@RequestParam("page") int page, @RequestParam("size") int size) {
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        Page<Project> projectPage = new Page<>(page , size); // 使用 size 作为页面大小
        projectPage = projectMapper.selectPage(projectPage, queryWrapper);
        return projectPage;
    }

    @Override
    public int getPageByID(@RequestParam("size") int size) {
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();

        // 获取总数据量
        int totalRecords = Math.toIntExact(projectMapper.selectCount(queryWrapper));
        // 计算总页数
        int totalPages = (int) Math.ceil((double) totalRecords / size);
        return totalPages;
    }

    @Override
    public List<Project> getAllProjects() {
        return baseMapper.selectList(null);
    }
}




