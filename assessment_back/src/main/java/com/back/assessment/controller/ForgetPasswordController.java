package com.back.assessment.controller;

import com.back.assessment.dto.RegistrationRequest;
import com.back.assessment.dto.Response;
import com.back.assessment.service.impl.RedisCacheServiceImpl;
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

    @Resource
    private RedisCacheServiceImpl redisCacheServiceImpl;

    @Description("根据邮箱发送验证码")
    @GetMapping("/mailForForgetPassword")
    public Response<String> sendEmail(@RequestParam("email") String email) {
        if(userService.selectUserByEmail(email)!=null){
            userService.mailSendForForgetPassword(email);
            return Response.successMail(email);
        }else{
            return Response.notFoundUser(email);
        }
    }

    @Description("重置密码")
    @GetMapping("/resetPassword")
    public Response<String> resetPassword(@RequestBody RegistrationRequest registrationRequest) {
        String email = registrationRequest.getEmail();
        String code = registrationRequest.getCode();
        String password = registrationRequest.getPassword();
        if (redisCacheServiceImpl.get(email) == null) return Response.codeExpired();
        else if (userService.codeCheckForForgetPassword(email, code)) {
            userService.changePassword(email, password);
            return Response.successResetPassword();
        } else {
            return Response.errorCode();
        }
    }
}
