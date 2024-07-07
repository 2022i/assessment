package com.back.assessment.controller;

import com.back.assessment.entity.Score;

import com.back.assessment.service.impl.ScoreServiceImpl;
import jakarta.annotation.Resource;
import jdk.jfr.Description;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/score")
public class ScoreController {
    @Resource
    private ScoreServiceImpl scoreService;

    @Description("根据项目ID给出对应项目所有的成绩信息")
    @GetMapping("/getScore")
    public Score getScore(@RequestParam int projectId) {
        return scoreService.selectScoreByProjectId(projectId);
    }


}
