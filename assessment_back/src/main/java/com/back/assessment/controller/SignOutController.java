package com.back.assessment.controller;

import cn.hutool.crypto.SecureUtil;
import com.back.assessment.dto.LoginByUsernameRequest;
import com.back.assessment.dto.Request;
import com.back.assessment.service.impl.SignOutServiceImpl;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/out")
public class SignOutController {
    @Resource
    SignOutServiceImpl signOutService;
    @PostMapping("/signOut")
    public Request<String> loginByUsername(@RequestHeader("X-Token") String token) {
        signOutService.signOut(token);
        return Request.successSignOut();
    }
}
