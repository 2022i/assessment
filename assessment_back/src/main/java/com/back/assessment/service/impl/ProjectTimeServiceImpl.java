package com.back.assessment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.assessment.entity.ProjectTime;
import com.back.assessment.service.ProjectTimeService;
import com.back.assessment.mapper.ProjectTimeMapper;
import org.springframework.stereotype.Service;

/**
* @author dell
* @description 针对表【project_time】的数据库操作Service实现
* @createDate 2024-07-04 16:12:30
*/
@Service
public class ProjectTimeServiceImpl extends ServiceImpl<ProjectTimeMapper, ProjectTime>
    implements ProjectTimeService{

    @Override
    public ProjectTime getProjectTimeById(int id) {
        return baseMapper.selectById(id);
    }
}




