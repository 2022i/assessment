package com.back.assessment.service.impl;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SignOutServiceImpl {
    @Resource
    private RedisCacheServiceImpl redisCacheService;
    public void signOut(String token) {
        redisCacheService.del(token);
    }
}
