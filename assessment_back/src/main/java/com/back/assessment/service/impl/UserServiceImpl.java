package com.back.assessment.service.impl;

import com.back.assessment.service.AbstractMailSend;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.assessment.entity.User;
import com.back.assessment.service.UserService;
import com.back.assessment.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
* @author lzz
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-07-01 19:30:36
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
    @Resource
    private MailSendForRegister mailSendForRegister;
    @Resource
    private MailSendForResetPassword mailSendForResetPassword;

    @Resource
    private RedisCacheServiceImpl redisCacheServiceImpl;

    @Resource
    private EncodingSelectionServiceImpl encodingSelectionService;

    @Override
    public User selectUserByEmail(String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        System.out.println(email);
        System.out.println(baseMapper.selectOne(queryWrapper));
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public User selectUserByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return baseMapper.selectOne(queryWrapper);
    }


    @Override
    public  boolean loginByEmail(String email, String password){
        User user = baseMapper.selectById(email);
        PasswordEncoder passwordEncoder = encodingSelectionService.getEncoder();
        return passwordEncoder.matches(password,user.getPassword());
    }

    @Override
    public  boolean loginByUsername(String username, String password){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = baseMapper.selectOne(queryWrapper);
        PasswordEncoder passwordEncoder = encodingSelectionService.getEncoder();
        System.out.println(password);
        System.out.println(user.getPassword());
        System.out.println(passwordEncoder.matches(password,user.getPassword()));
        return passwordEncoder.matches(password,user.getPassword());
    }

    @Override
    public void mailForRegister(String email){
        mailSendForRegister.sendMail(email);
    }

    @Override
    public void mailSendForForgetPassword(String email){
        mailSendForResetPassword.sendMail(email);
    }

    @Override
    public boolean registerUser(String username, String email,String password,String code){
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

    @Override
    public boolean codeCheckForForgetPassword(String email, String code){
        return redisCacheServiceImpl.get(email).equals(code);
    }

    @Override
    public void makePasswordNull(String email){
        User user = baseMapper.selectById(email);
        user.setPassword(null);
        baseMapper.updateById(user);
    }

    @Override
    public void changePassword(String email, String password){
        User user = baseMapper.selectById(email);
        PasswordEncoder passwordEncoder = encodingSelectionService.getEncoder();
        String passwordEncoded = passwordEncoder.encode(password);
        user.setPassword(passwordEncoded);
        baseMapper.updateById(user);
    }
}




