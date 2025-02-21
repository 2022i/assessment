package com.back.assessment.service.impl;

import com.back.assessment.entity.User;
import com.back.assessment.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserLogin extends ServiceImpl<UserMapper, User> {
    @Resource
    private EncodingSelectionServiceImpl encodingSelectionService;
    public  boolean loginByEmail(String email, String password){
        User user = baseMapper.selectById(email);
        PasswordEncoder passwordEncoder = encodingSelectionService.getEncoder();
        return passwordEncoder.matches(password,user.getPassword());
    }

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

}
