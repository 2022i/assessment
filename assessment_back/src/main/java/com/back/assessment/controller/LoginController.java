package com.back.assessment.controller;

import com.back.assessment.dto.LoginByEmailRequest;
import com.back.assessment.dto.LoginByUsernameRequest;
import com.back.assessment.dto.Request;
import com.back.assessment.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    @Resource
    private AuthenticationManager authenticationManager;

    @PostMapping("/loginByUsername")
    public Request<String> loginByUsername(@RequestBody LoginByUsernameRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
//        Authentication usernamePasswordAuthentication =
//                UsernamePasswordAuthenticationToken.unauthenticated(username, password);
        if (userService.selectUserByUsername(username)!=null) {
            if (userService.loginByUsername(username, password)) {
                return Request.successLogin(username);
            } else {
                return Request.errorPassword();
            }
        }
        return Request.notFoundUser(username);
    }

    @PostMapping("/loginByEmail")
    public Request<String> loginByEmail(@RequestBody LoginByEmailRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        if (userService.selectUserByEmail(email)!=null) {
            if (userService.loginByEmail(email, password)) {
                return Request.successLogin(email);
            } else {
                return Request.errorPassword();
            }

        }
        return Request.notFoundUser(email);
    }
}
