package com.back.assessment.mapper;

import com.back.assessment.entity.Score;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
* @author 23831
* @description 针对表【score】的数据库操作Mapper
* @createDate 2024-07-02 20:21:07
* @Entity com.back.assessment.entity.Score
*/
@Mapper
public interface ScoreMapper extends BaseMapper<Score> {

}




