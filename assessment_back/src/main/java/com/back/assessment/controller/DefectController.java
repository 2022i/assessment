package com.back.assessment.controller;

import com.back.assessment.entity.Defect;
import com.back.assessment.service.DefectService;
import jakarta.annotation.Resource;
import jdk.jfr.Description;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/defect")
public class DefectController {
    @Resource
    private DefectService defectService;

    @Description("根据项目ID给出对应项目缺陷表的所有信息")
    @GetMapping("/getDefectById")
    public Defect getDefectById(@RequestParam int id){
        return   defectService.getDefectById(id);
    }

}
