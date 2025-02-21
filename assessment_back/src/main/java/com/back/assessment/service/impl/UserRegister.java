package com.back.assessment.service.impl;

import com.back.assessment.dto.RegistrationRequest;
import com.back.assessment.entity.User;
import com.back.assessment.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserRegister extends ServiceImpl<UserMapper, User> {
    @Resource
    private MailSendForRegister mailSendForRegister;
    @Resource
    private RedisCacheServiceImpl redisCacheServiceImpl;
    @Resource
    private EncodingSelectionServiceImpl encodingSelectionService;
    public void mailForRegister(String email){
        mailSendForRegister.sendMail(email);
    }

    public boolean registerUser(RegistrationRequest registrationRequest){
        String username = registrationRequest.getUsername();
        String email = registrationRequest.getEmail();
        String password = registrationRequest.getPassword();
        String code = registrationRequest.getCode();
        if(redisCacheServiceImpl.get(email).equals(code)){
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            PasswordEncoder passwordEncoder = encodingSelectionService.getEncoder();
            String passwordEncoded = passwordEncoder.encode(password);
            user.setPassword(passwordEncoded);
            baseMapper.insert(user);
            return true;
        }
        return false;
    }
}
