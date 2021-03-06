package com.df.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/3/2 22:41
 **/
public class StatisticController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("getPageVisits")
    public ResponseEntity<String> getPageVisits() {
//        redisTemplate.opsForValue().
        return ResponseEntity.ok().body("1");
    }
}
