package com.back.assessment.controller;

import com.back.assessment.dto.LoginRequest;
import com.back.assessment.dto.Request;
import com.back.assessment.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lzz
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    private UserServiceImpl userService;

    @PostMapping("/loginByUsername")
    public Request<String> loginByUsername(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        if (userService.selectUserByUsername(username)) {
            if (userService.loginByUsername(username, password)) {
                return Request.successLogin(username);
            } else {
                return Request.errorPassword();
            }

        }
        return Request.notFound();
    }

    @PostMapping("/loginByEmail")
    public Request<String> loginByEmail(@RequestBody LoginRequest loginRequest) {
        System.out.println("进入了loginByEmail接口");
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        if (userService.selectUserByEmail(email)) {
            System.out.println("进入了selectUserByEmail");
            if (userService.loginByEmail(email, password)) {
                System.out.println("进入了loginByEmail");
                return Request.successLogin(email);
            } else {
                return Request.errorPassword();
            }

        }
        return Request.notFound();
    }
}
