package com.df.service;

import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


        List<Object> execute = redisTemplate.execute(new SessionCallback<List<Object>>() {
            @Override
            public List<Object> execute(RedisOperations operations) throws DataAccessException {
                operations.watch(storeKey);
                String store = (String) operations.opsForValue().get(storeKey);
                if (store == null) {
                    System.out.println("秒杀还未开始");
                    return null;
                }
                if (Integer.parseInt(store) <= 0) {
                    System.out.println("库存没了 秒杀结束");
                    return null;
                }
                // 判断用户是否重复秒杀操作
                Boolean member = operations.opsForSet().isMember(userKey, uid);
                if (member) {
                    System.out.println("已经成功抢到 无法重复秒杀");
                }
                // 监视库存
                operations.watch(storeKey);
                operations.multi();
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


    public boolean doSecKill2(String uid, String prodId) {

        // uid和prod id 非空判断
        if (uid == null || prodId == null) {
            return false;
        }
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("SecKill.lua")));
        redisScript.setResultType(Long.class);
        List<String> list = new ArrayList<>();
        list.add(prodId);
        list.add(uid);
        Long res = redisTemplate.execute(redisScript, list);
        if (res == 0L) {
            System.out.println("已抢空！！");
        } else if (res == 1L) {
            System.out.println("抢购成功");
            return true;
        } else if (res == 2L) {
            System.out.println("该用户已经抢过");
        } else {
            System.out.println("抢购异常！！！！");
        }
        return false;
    }

    public void testLock() {
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", "111");
        if (lock != null && lock) {
            String value = this.redisTemplate.opsForValue().get("num");
            if (StringUtil.isNullOrEmpty(value)) {
                return;
            }
            int num = Integer.parseInt(value);
            this.redisTemplate.opsForValue().set("num", ++num + "");
            this.redisTemplate.delete(("lock"));
        } else {
            try {
                Thread.sleep(1000);
                testLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
