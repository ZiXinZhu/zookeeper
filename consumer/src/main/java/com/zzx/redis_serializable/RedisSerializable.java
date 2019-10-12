package com.zzx.redis_serializable;

import com.zzx.ConsumerApplication;
import com.zzx.po.UserPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest(classes = ConsumerApplication.class)
@RunWith(SpringRunner.class)
public class RedisSerializable {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;
//    RedisTemplate redisTemplate;

    @Test
    public void setUser(){
        UserPO userPO= (UserPO) redisTemplate.opsForValue().get("girl");
        System.out.println(userPO);
    }
}
