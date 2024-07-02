package com.back.assessment.service;

import java.util.concurrent.TimeUnit;

/**
 * @author lzz
 */
public interface RedisCacheService {

    void set(String key, String value,long timeout, TimeUnit unit) ;

    String get(String key) ;

    Long del(String... key) ;
}
