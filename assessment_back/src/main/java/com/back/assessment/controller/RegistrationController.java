package com.back.assessment.controller;

import com.back.assessment.dto.LoginRequest;
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
    public Response<String> mailMail(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String username = loginRequest.getUsername();
        if (userService.selectUserByEmail(email)==null) {
            if(userService.selectUserByUsername(username)==null){
               userService.mailForRegister(email);
               return Response.successMail(email);
            }else{
                return Response.alreadyExistsUsername(username);
            }


        }else{
            return Response.alreadyExistsEmail(email);
        }
    }

    @Description("收到验证码并且将用户名、密码、验证码输入完毕后注册")
    @PostMapping("/registerByEmail")
    public Response<String> registerByEmail(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        String email = loginRequest.getEmail();
        String code = loginRequest.getCode();
        if(redisCacheServiceImpl.get(email)!=null){
            if(userService.registerUser(username,email,password,code)){
                return Response.successRegister(username);
            }
            else {
                return Response.errorCode();
            }
        }else {
            return Response.codeExpired();
        }
    }
}
