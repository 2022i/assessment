package com.back.assessment.controller;

import com.back.assessment.entity.Score;

import com.back.assessment.service.impl.ScoreServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/score")
public class ScoreController {
    @Resource
    private ScoreServiceImpl scoreService;

    @GetMapping("/getScore")
    public Score getScore(@RequestParam int projectId) {
        return scoreService.selectScoreByProjectId(projectId);
    }


}
