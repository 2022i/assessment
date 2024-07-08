package com.back.assessment.controller;

import com.back.assessment.dto.Response;
import com.back.assessment.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import jdk.jfr.Description;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/forget")
public class ForgetPasswordController {
    @Resource
    private UserServiceImpl userService;

    @Description("根据邮箱发送验证码")
    @PostMapping("/mailForForgetPassword")
    public Response<String> recoverPassword(@RequestParam String email) {
        System.out.println(email);
        if(userService.selectUserByEmail(email)!=null){
            userService.mailSendForForgetPassword(email);
            return Response.successMail(email);
        }else{
            return Response.notFoundUser(email);
        }
    }

    @Description("重置密码")
    @PostMapping("/resetPassword")
    public Response<String> resetPassword(@RequestParam String email, @RequestParam String code, @RequestParam String password) {
        if(userService.codeCheckForForgetPassword(email,code)){
            userService.makePasswordNull(email);
            userService.changePassword(email,password);
            return Response.success();
        }else{
            return Response.errorCode();
        }
    }
}
