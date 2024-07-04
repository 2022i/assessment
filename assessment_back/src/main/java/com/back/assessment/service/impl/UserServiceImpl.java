package com.back.assessment.service.impl;

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
    private MailServiceImpl mailServiceImpl;

    @Resource
    private RedisCacheServiceImpl redisCacheServiceImpl;

    @Resource
    private EncodingSelectionServiceImpl encodingSelectionService;
    @Override
    public User selectUserByEmail(String email) {

        return baseMapper.selectById(email);
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
        return passwordEncoder.matches(password,user.getPassword());
    }

    @Override
    public void mailMail(String email){
        mailServiceImpl.mailSend(email);
    }

    @Override
    public boolean registerUser(String username, String email,String password,String code){
        if(redisCacheServiceImpl.get(email).equals(code)){
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            baseMapper.insert(user);
            return true;
        }
        return false;
    }



}




