package com.back.assessment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.assessment.entity.ProjectPerson;
import com.back.assessment.service.ProjectPersonService;
import com.back.assessment.mapper.ProjectPersonMapper;
import org.springframework.stereotype.Service;

/**
* @author dell
* @description 针对表【project_person】的数据库操作Service实现
* @createDate 2024-07-04 16:25:16
*/
@Service
public class ProjectPersonServiceImpl extends ServiceImpl<ProjectPersonMapper, ProjectPerson>
    implements ProjectPersonService{

    @Override
    public ProjectPerson getProjectPersonById(int id) {
        return baseMapper.selectById(id);
    }
}




