package com.back.assessment.mapper;

import com.back.assessment.entity.ProjectPerson;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author dell
* @description 针对表【project_person】的数据库操作Mapper
* @createDate 2024-07-04 16:25:16
* @Entity com.back.assessment.entity.ProjectPerson
*/
@Mapper
public interface ProjectPersonMapper extends BaseMapper<ProjectPerson> {

}




