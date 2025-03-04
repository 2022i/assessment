package com.back.assessment.service;

import com.back.assessment.entity.Project;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @author dell
* @description 针对表【project】的数据库操作Service
* @createDate 2024-07-02 11:03:29
*/
public interface ProjectService extends IService<Project> {
        Project getProjectById(int id);
        void changeProjectWeight(int projectId, int weightId);
        List<Project> getAllProjects();
}
