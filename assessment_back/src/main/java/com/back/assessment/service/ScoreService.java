package com.back.assessment.service;

import com.back.assessment.entity.Score;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 23831
* @description 针对表【score】的数据库操作Service
* @createDate 2024-07-02 20:21:07
*/
public interface ScoreService extends IService<Score> {
    // 查询所有数据
    public Score selectScoreByProjectId(int projectId);

}
