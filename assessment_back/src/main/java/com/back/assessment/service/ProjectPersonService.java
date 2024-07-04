package com.back.assessment.service;

import com.back.assessment.entity.ProjectPerson;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author dell
* @description 针对表【project_person】的数据库操作Service
* @createDate 2024-07-04 16:25:16
*/
public interface ProjectPersonService extends IService<ProjectPerson> {
    ProjectPerson getProjectPersonById(int id);
}
