package com.df.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/4/29 23:33
 **/
@RestController
@RequestMapping("/redisTest")
public class RedisTestController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/getName")
    public String testRedis(){
        redisTemplate.opsForValue().set("name","lucy");
        String name = redisTemplate.opsForValue().get("name");
        return name;
    }
}
