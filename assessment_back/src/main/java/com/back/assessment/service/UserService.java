package com.back.assessment.service;

import com.back.assessment.dto.RegistrationRequest;
import com.back.assessment.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 23831
* @description 针对表【user】的数据库操作Service
* @createDate 2024-07-01 19:30:36
*/
public interface UserService extends IService<User> {
    User selectUserByEmail(String email);
    User selectUserByUsername(String username);
    boolean loginByEmail(String username, String password);
    boolean loginByUsername(String username,  String password);
    void mailForRegister(String email);
    void mailSendForForgetPassword(String email);
    boolean registerUser(RegistrationRequest registrationRequest);
    boolean codeCheckForForgetPassword(String email, String code);
    void changePassword(String email, String password);
}
