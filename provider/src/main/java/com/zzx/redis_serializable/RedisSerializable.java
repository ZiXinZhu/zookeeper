package com.zzx.redis_serializable;

import com.zzx.ProviderApplication;
import com.zzx.po.UserPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;



@SpringBootTest(classes = ProviderApplication.class)
@RunWith(SpringRunner.class)
public class RedisSerializable {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;
//    RedisTemplate redisTemplate;

    @Test
    public void setUser(){
        UserPO userPO=new UserPO();
        userPO.setId(1);
        userPO.setAge(18);
        userPO.setHigh(167);
        redisTemplate.opsForValue().set("girl",userPO);
        System.out.println(redisTemplate.opsForValue().get("girl"));
    }
}
