package com.back.assessment.mapper;

import com.back.assessment.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author lzz
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-07-01 19:30:36
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




