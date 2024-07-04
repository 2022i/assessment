package com.back.assessment.controller;

import com.back.assessment.entity.Defect;
import com.back.assessment.service.DefectService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/defect")
public class DefectController {
    @Resource
    private DefectService defectService;
    @GetMapping("/getDefectById")
    public Defect getDefectById(@RequestParam int id){
        return   defectService.getDefectById(id);
    }

}
