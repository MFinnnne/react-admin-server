package com.df.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

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
        String storeKey = "sk:" + prodId + ":qt";
        // 秒杀成功用户key
        String userKey = "sk:" + uid + ":user";

        String store = redisTemplate.opsForValue().get(storeKey);
        if (store == null) {
            System.out.println("秒杀还未开始");
            return false;
        }
        if (Integer.parseInt(store) == 0){
            System.out.println("库存没了 秒杀结束");
            return false;
        }
        // 判断用户是否重复秒杀操作
        Boolean member = redisTemplate.opsForSet().isMember(userKey, uid);
        if (member) {
            System.out.println("已经成功抢到 无法重复秒杀");
            return false;
        }
        List<Object> execute = redisTemplate.execute(new SessionCallback<List<Object>>() {
            @Override
            public List<Object> execute(RedisOperations operations) throws DataAccessException {

                // 监视库存
                operations.watch(storeKey);
                operations.multi();
                //4 获取库存,如果库存null，秒杀还没有开始

                // 秒杀的过程
                // 成功 库存减1
                operations.opsForValue().decrement(storeKey);
                // 把秒杀成功用户添加到清单里面
                operations.opsForSet().add(userKey, uid);
                return operations.exec();
            }
        });
        if (execute == null || execute.size() == 0) {
            System.out.println(userKey + " 秒杀失败");
            return false;
        }
        System.out.println(userKey + " 秒杀成功");
        return false;
    }
}
