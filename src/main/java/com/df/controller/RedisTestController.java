package com.df.controller;

import com.df.service.RedisTestService;
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

    @Autowired
    private RedisTestService redisTestService;

    @GetMapping("/getName")
    public String testRedis() {
        redisTemplate.opsForValue().set("name", "lucy");
        return redisTemplate.opsForValue().get("name");
    }

    @GetMapping("/secKill")
    public String secKill(String uid, String prodId) {
        boolean kill = redisTestService.doSecKill2(uid, prodId);
        if (kill) {
            return "秒杀成功";
        }
        return "秒杀失败";
    }

    @GetMapping("/testLock")
    public void testLock() {
        this.redisTestService.testLock();
    }
}
