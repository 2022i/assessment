package com.back.assessment.service;

import com.back.assessment.entity.ProjectTime;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author dell
* @description 针对表【project_time】的数据库操作Service
* @createDate 2024-07-04 16:12:30
*/
public interface ProjectTimeService extends IService<ProjectTime> {
   ProjectTime getProjectTimeById(int id);
}
