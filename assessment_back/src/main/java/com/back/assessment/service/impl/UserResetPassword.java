package com.back.assessment.service.impl;

import com.back.assessment.entity.User;
import com.back.assessment.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserResetPassword extends ServiceImpl<UserMapper, User> {
    @Resource
    private MailSendForResetPassword mailSendForResetPassword;
    @Resource
    private RedisCacheServiceImpl redisCacheServiceImpl;
    @Resource
    private EncodingSelectionServiceImpl encodingSelectionService;

    public void mailSendForForgetPassword(String email){
        mailSendForResetPassword.sendMail(email);
    }

    public boolean codeCheckForForgetPassword(String email, String code){
        return redisCacheServiceImpl.get(email).equals(code);
    }

    public void changePassword(String email, String password){
        User user = baseMapper.selectById(email);
        PasswordEncoder passwordEncoder = encodingSelectionService.getEncoder();
        String passwordEncoded = passwordEncoder.encode(password);
        user.setPassword(passwordEncoded);
        baseMapper.updateById(user);
    }
}
