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
@RequestMapping("/register")
public class RegistrationController {
    @Resource
    private UserServiceImpl userService;

    @PostMapping("/mailMail")
    public Request<String>mailMail(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String username = loginRequest.getUsername();
        if (userService.selectUserByEmail(email)==null) {
            if(userService.selectUserByUsername(username)==null){
               userService.mailMail(email);
               return Request.successMail(email);
            }else{
                return Request.alreadyExistsUsername(username);
            }


        }else{
            return Request.alreadyExistsEmail(email);
        }
    }

    @PostMapping("/registerByEmail")
    public Request<String> registerByEmail(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        String email = loginRequest.getEmail();
        String code = loginRequest.getCode();
        if(userService.registerUser(username,email,password,code)){
            return Request.successRegister(username);
        }
        else {
            return Request.errorCode();
        }
    }



}
