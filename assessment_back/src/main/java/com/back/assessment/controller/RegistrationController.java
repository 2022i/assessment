package com.back.assessment.controller;

import com.back.assessment.dto.RegistrationRequest;
import com.back.assessment.dto.Response;
import com.back.assessment.service.impl.RedisCacheServiceImpl;
import com.back.assessment.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import jdk.jfr.Description;
import org.springframework.web.bind.annotation.*;

/**
 * @author lzz
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/register")
public class RegistrationController {
    @Resource
    private UserServiceImpl userService;

    @Resource
    private RedisCacheServiceImpl redisCacheServiceImpl;

    @Description("根据邮箱发送验证码")
    @PostMapping("/mailForRegister")
    public Response<String> mailMail(@RequestBody RegistrationRequest registrationRequest) {
        String email = registrationRequest.getEmail();
        String username = registrationRequest.getUsername();
        if(userService.selectUserByEmail(email)!=null){
            return Response.alreadyExistsEmail(email);
        }else if(userService.selectUserByUsername(username)!=null){
            return Response.alreadyExistsUsername(username);
        }
        userService.mailForRegister(email);
        return Response.successMail(email);
    }

    @Description("收到验证码并且将用户名、密码、验证码输入完毕后注册")
    @PostMapping("/registerByEmail")
    public Response<String> registerByEmail(@RequestBody RegistrationRequest registrationRequest) {
        String username = registrationRequest.getUsername();
        String password = registrationRequest.getPassword();
        String email = registrationRequest.getEmail();
        String code = registrationRequest.getCode();
        if(redisCacheServiceImpl.get((email))==null){
            return Response.codeExpired();
        }else if(userService.registerUser(username,email,password,code)){
            return Response.successRegister(username);
        }else {
            return Response.errorCode();
        }
    }
}
