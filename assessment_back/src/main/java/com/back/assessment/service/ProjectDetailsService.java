package com.back.assessment.service;

import com.back.assessment.entity.ProjectDetails;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author dell
* @description 针对表【project_details】的数据库操作Service
* @createDate 2024-07-08 11:00:03
*/
public interface ProjectDetailsService extends IService<ProjectDetails> {
        ProjectDetails getProjectDetails(Integer projectId);
}
