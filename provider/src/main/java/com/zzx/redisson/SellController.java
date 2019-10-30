package com.zzx.redisson;


import com.zzx.redis.TestRedisTemplate;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class SellController {



    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedissonClient redisson;

    @GetMapping("/save")
    public void add(){
        redisTemplate.delete("zzx");
        redisTemplate.opsForValue().set("zzx",321);
        System.out.println("添加成功");
    }

    private final String LOCKKEY = "locks";

    @GetMapping("/sell")
    public void sell(){
        for(int i=0; i < 55; i++){
            RLock lock = redisson.getLock(LOCKKEY);
            lock.lock(60, TimeUnit.SECONDS); //加锁，60秒后自动释放锁 （默认30秒）
            int stock = Integer.parseInt(String.valueOf(redisTemplate.opsForValue().get("zzx")));
            if(stock > 0){
                redisTemplate.opsForValue().set("zzx",(stock-1)+"");
                System.out.println(LOCKKEY+":"+(stock-1)+"");
            }
            lock.unlock(); //释放锁
        }
    }
}
