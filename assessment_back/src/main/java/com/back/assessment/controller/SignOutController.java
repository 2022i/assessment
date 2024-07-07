package com.back.assessment.controller;

import com.back.assessment.dto.Response;
import com.back.assessment.service.impl.SignOutServiceImpl;
import jakarta.annotation.Resource;
import jdk.jfr.Description;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/out")
public class SignOutController {
    @Resource
    SignOutServiceImpl signOutService;

    @Description("退出登录,根据请求头的token，删除缓存中的用户信息")
    @PostMapping("/signOut")
    public Response<String> loginByUsername(@RequestHeader("X-Token") String token) {
        signOutService.signOut(token);
        return Response.successSignOut();
    }
}
