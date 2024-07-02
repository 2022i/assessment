package com.back.assessment.mapper;

import com.back.assessment.entity.Project;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author dell
* @description 针对表【project】的数据库操作Mapper
* @createDate 2024-07-02 11:03:29
* @Entity com.back.assessment.entity.Project
*/
@Mapper
public interface ProjectMapper extends BaseMapper<Project> {

//    List<Project> fuzzy_search(String name);
}




