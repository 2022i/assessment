package com.back.assessment.service.impl;

import com.back.assessment.mail.MailMail;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.back.assessment.entity.User;
import com.back.assessment.service.UserService;
import com.back.assessment.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
* @author lzz
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-07-01 19:30:36
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
    @Resource
    private MailMail mailMail;

    @Resource
    private RedisCacheServiceImpl redisCacheServiceImpl;
    @Override
    public boolean selectUserByEmail(String email) {

        User user = baseMapper.selectById(email);
        return user != null;
    }

    @Override
    public boolean selectUserByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = baseMapper.selectOne(queryWrapper);
        return user != null;
    }


    @Override
    public  boolean loginByEmail(String email, String password){
        User user = baseMapper.selectById(email);
        return user.getPassword().equals(password);
    }

    @Override
    public  boolean loginByUsername(String username, String password){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = baseMapper.selectOne(queryWrapper);
        return user.getPassword().equals(password);
    }

    @Override
    public void mailMail(String email){
        mailMail.mailSend(email);
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




