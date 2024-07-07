package com.back.assessment.controller;

import cn.hutool.crypto.SecureUtil;
import com.back.assessment.dto.LoginByEmailRequest;
import com.back.assessment.dto.LoginByUsernameRequest;
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
@RequestMapping("/login")
public class LoginController {
    @Resource
    private UserServiceImpl userService;

    @Resource
    private RedisCacheServiceImpl redisCacheService;

    @Description("根据用户名和密码登录")
    @PostMapping("/loginByUsername")
    public Response<String> loginByUsername(@RequestBody LoginByUsernameRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        if (userService.selectUserByUsername(username)!=null) {
            if (userService.loginByUsername(username, password)) {
                String token = SecureUtil.md5(loginRequest.toString());
                redisCacheService.set(token,loginRequest.getUsername());
                return Response.successLogin(token);
            } else {
                return Response.errorPassword();
            }
        }
        return Response.notFoundUser(username);
    }

//    @GetMapping("/loginByUsername")
//    public Response<String> loginByUsername(@RequestParam String username , @RequestParam String password) {
//        if (userService.selectUserByUsername(username)!=null) {
//            if (userService.loginByUsername(username, password)) {
//                String token = SecureUtil.md5(username+password);
//                redisCacheService.set(token,username);
//                return Response.successLogin(token);
//            } else {
//                return Response.errorPassword();
//            }
//        }
//        return Response.notFoundUser(username);
//    }

    @Description("根据邮箱和密码登录")
    @PostMapping("/loginByEmail")
    public Response<String> loginByEmail(@RequestBody LoginByEmailRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        if (userService.selectUserByEmail(email)!=null) {
            if (userService.loginByEmail(email, password)) {
                return Response.successLogin(email);
            } else {
                return Response.errorPassword();
            }
        }
        return Response.notFoundUser(email);
    }
}
