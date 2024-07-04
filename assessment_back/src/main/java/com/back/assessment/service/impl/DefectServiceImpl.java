package com.back.assessment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.assessment.entity.Defect;
import com.back.assessment.service.DefectService;
import com.back.assessment.mapper.DefectMapper;
import org.springframework.stereotype.Service;

/**
* @author 23831
* @description 针对表【defect】的数据库操作Service实现
* @createDate 2024-07-02 21:02:31
*/
@Service
public class DefectServiceImpl extends ServiceImpl<DefectMapper, Defect>
    implements DefectService{
    @Override
    public void changeWeightId(int projectId,int weightId) {
        //将指定id号的Defect实体的weightId修改为weightId
        //查询出指定id号的Defect实体
        Defect defect = this.getById(projectId);
        //修改weightId
        defect.setWeightId(weightId);
        //更新
        baseMapper.updateById(defect);
    }

    @Override
    public Defect getDefectById(int id) {
        return baseMapper.selectById(id);
    }

}




