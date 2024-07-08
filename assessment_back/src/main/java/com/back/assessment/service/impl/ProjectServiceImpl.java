package com.back.assessment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.assessment.entity.Project;
import com.back.assessment.service.ProjectService;
import com.back.assessment.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @author dell
*/
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService{

    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public Project getProjectById(int id) {
        return baseMapper.selectById(id);
    }

//    @Override
//    public List<Project> fuzzy_search(String name) {
//        return baseMapper.fuzzy_search(name);
//    }

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

//    @Override
//    public Page<Project> getProjectByname(String name, int page, int size) {
//        //分页查询，返回分页对象
//        Page<Project> pageInfo = new Page<>(page, size);
//        //查询条件
//        return baseMapper.selectPage(pageInfo, null);
//        return 0;
//    }

    @Override
    public Page<Project> getProjectByPage(@RequestParam("page") int page, @RequestParam("size") int size) {
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        Page<Project> projectPage = new Page<>(page , size); // 使用 size 作为页面大小
//        queryWrapper.like("name", "%" + name + "%");
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

//        // 创建 Page 对象，并设置总页数
//        Page<Project> projectPage = new Page<>(page - 1, size, totalRecords);
//        // 执行分页查询
//        projectPage = projectMapper.selectPage(projectPage, queryWrapper);
        return totalPages;
    }

    @Override
    public List<Project> getAllProjects() {
        return baseMapper.selectList(null);
    }
}




