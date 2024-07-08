package com.back.assessment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.assessment.entity.ProjectDetails;
import com.back.assessment.service.ProjectDetailsService;
import com.back.assessment.mapper.ProjectDetailsMapper;
import org.springframework.stereotype.Service;

/**
* @author dell
* @description 针对表【project_details】的数据库操作Service实现
* @createDate 2024-07-08 11:45:31
*/
@Service
public class ProjectDetailsServiceImpl extends ServiceImpl<ProjectDetailsMapper, ProjectDetails>
    implements ProjectDetailsService{

    @Override
    public ProjectDetails getProjectDetails(Integer projectId) {
        return baseMapper.selectById(projectId);
    }
}




