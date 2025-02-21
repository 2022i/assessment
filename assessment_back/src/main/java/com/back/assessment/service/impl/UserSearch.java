package com.back.assessment.service.impl;

import com.back.assessment.entity.User;
import com.back.assessment.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserSearch extends ServiceImpl<UserMapper, User> {
    public User selectUserByEmail(String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        System.out.println(email);
        System.out.println(baseMapper.selectOne(queryWrapper));
        return baseMapper.selectOne(queryWrapper);
    }

    public User selectUserByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return baseMapper.selectOne(queryWrapper);
    }


}
