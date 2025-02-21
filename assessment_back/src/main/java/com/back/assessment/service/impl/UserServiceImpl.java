package com.back.assessment.service.impl;
import com.back.assessment.dto.RegistrationRequest;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.assessment.entity.User;
import com.back.assessment.service.UserService;
import com.back.assessment.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author lzz
* &#064;description  针对表【user】的数据库操作Service实现
* &#064;createDate  2024-07-01 19:30:36
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
    @Resource
    private UserSearch userSearch;
    @Resource
    private UserLogin userLogin;
    @Resource
    private UserRegister userRegister;
    @Resource
    private UserResetPassword userResetPassword;

    @Override
    public User selectUserByEmail(String email) {
        return userSearch.selectUserByEmail(email);
    }

    @Override
    public User selectUserByUsername(String username) {
        return userSearch.selectUserByUsername(username);
    }

    @Override
    public  boolean loginByEmail(String email, String password){
        return userLogin.loginByEmail(email,password);
    }

    @Override
    public  boolean loginByUsername(String username, String password){
        return userLogin.loginByUsername(username,password);
    }

    @Override
    public void mailForRegister(String email){
        userRegister.mailForRegister(email);
    }

    @Override
    public void mailSendForForgetPassword(String email){
        userResetPassword.mailSendForForgetPassword(email);
    }

    @Override
    public boolean registerUser(RegistrationRequest registrationRequest){
        return userRegister.registerUser(registrationRequest);
    }

    @Override
    public boolean codeCheckForForgetPassword(String email, String code){
        return userResetPassword.codeCheckForForgetPassword(email,code);
    }

    @Override
    public void changePassword(String email, String password){
        userResetPassword.changePassword(email,password);
    }
}




