package com.online.retailer.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

public class RedisDAO {
    //对redis数据库进行增删改查
    @Autowired
    StringRedisTemplate stringRedisTemplate;
}
