package com.df.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/5/7 22:19
 **/
@Service
public class RedisTestService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean doSecKill(String uid, String prodId) {
        // uid和prod id 非空判断
        if (uid == null || prodId == null) {
            return false;
        }
        // 拼接key
        // 库存key
        String storeKey = "sk:" + prodId + ":qt";
        // 秒杀成功用户key
        String userKey = "sk:" + prodId + ":user";
        // 监视库存
        redisTemplate.watch(storeKey);

        //4 获取库存,如果库存null，秒杀还没有开始
        String store = redisTemplate.opsForValue().get(storeKey);
        if (store == null) {
            System.out.println("秒杀还未开始");
        }
        // 判断用户是否重复秒杀操作
        Boolean sismember = redisTemplate.opsForSet().isMember(userKey, uid);

        if (sismember != null && sismember) {
            System.out.println("已经秒杀成功，不能重复秒杀");
            return false;
        }
        // 判断如果商品的数量，库存的数量小于1 表示商品没了 秒杀结束
        assert store != null;
        if (Integer.parseInt(store) <= 0) {
            System.out.println("秒杀结束了");
        }

        SessionCallback<Object> sessionCallback = new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                // 秒杀的过程
                // 成功 库存减1
                StringRedisTemplate redisTemplate = (StringRedisTemplate) operations;
                redisTemplate.opsForValue().decrement(storeKey);
                // 把秒杀成功用户添加到清单里面
                redisTemplate.opsForSet().add(userKey, uid);
                return redisTemplate.exec();
            }

        };
        redisTemplate.execute(sessionCallback);
        System.out.println("秒杀成功");
        return true;
    }
}
