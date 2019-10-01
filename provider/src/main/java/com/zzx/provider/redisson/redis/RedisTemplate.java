package com.zzx.provider.redisson.redis;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RedisTemplate {
    private static final long TIMEOUT=60*1000;


    @Autowired
    org.springframework.data.redis.core.RedisTemplate<String, Object> redisTemplate;



    public boolean lock(String key, String value) {
        //SETNX命令, 可以设置返回true, 不可以返回false
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }
        String currentValue = String.valueOf(redisTemplate.opsForValue().get(key));
        //如果锁过期
        if (StringUtils.isEmpty(currentValue) && (Long.parseLong(currentValue) < System.currentTimeMillis())) {
            //GETSET命令, 获取上一个锁的时间
            String oldValue = String.valueOf(redisTemplate.opsForValue().getAndSet(key, value));
            return !StringUtils.isEmpty(oldValue) && oldValue.equals(value);
        }
        return false;
    }
    /**
     * 解锁
     */
    public void unLock(String key, String value) {
        try {
            String currentValue = String.valueOf(redisTemplate.opsForValue().get(key));
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Throwable e) {
            log.error("[redis分布式锁] 解锁异常, {}", e.getMessage(), e);
        }
    }




    @GetMapping("/redis/template")
    public void orderProductMockDiffUser(String productId) {
        //加锁
        long time = System.currentTimeMillis() + TIMEOUT;
        if (!lock(productId, String.valueOf(time))) {
            throw new RuntimeException("人也太多了, 换个姿势再试试～");
        }

        //解锁
        unLock(productId, String.valueOf(time));
    }
}
