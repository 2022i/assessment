package com.back.assessment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.assessment.entity.Project;
import com.back.assessment.service.ProjectService;
import com.back.assessment.mapper.ProjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author dell
*/
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService{

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


}




