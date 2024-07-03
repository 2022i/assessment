package com.back.assessment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.assessment.entity.Score;
import com.back.assessment.service.ScoreService;
import com.back.assessment.mapper.ScoreMapper;
import org.springframework.stereotype.Service;

/**
* @author 23831
* @description 针对表【score】的数据库操作Service实现
* @createDate 2024-07-02 20:21:07
*/
@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score>
    implements ScoreService{
    @Override
    public Score selectScoreByProjectId(int projectId) {
        //返回指定projectId的score
        return baseMapper.selectById(projectId);
    }

}




