package com.zzx.consumer.redisson;



import com.zzx.consumer.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DoRedisTemplate {

    private static final long TIMEOUT=30*1000;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    @Autowired
    UserDao userDao;

    public boolean lock(String key, String value) {

        String currentValue=String.valueOf(redisTemplate.opsForValue().get(key));
        //setIfAbsent如果key存在false, 如果不存在返回true
        if (!redisTemplate.opsForValue().setIfAbsent(key, value)) {
            if (currentValue.equals("null") || ((Long.parseLong(currentValue) > System.currentTimeMillis()))) {
                return false;
            }
            System.out.println("time:" + currentValue);
            //setIfAbsent如果key存在false, 如果不存在返回true
            System.out.println("add lock true");
            redisTemplate.opsForValue().set(key, value);
            return true;
        } else {
            System.out.println("add lock true");
            redisTemplate.opsForValue().set(key, value);
            return true;
        }
    }
    /**
     * 解锁
     */
    public void unLock(String key, String value) {
        try {
            String currentValue = String.valueOf(redisTemplate.opsForValue().get(key));
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                System.out.println("unlock success");
                redisTemplate.delete(key);
            }
        } catch (Throwable e) {
            log.error("[redis分布式锁] 解锁异常, {}", e.getMessage(), e);
        }
    }

    @GetMapping("/temp")
    public void order() {
        log.info("starting lock!");
        //加锁
        for (int i=0;i<50;i++){
        String goodsId="1";
        long time = System.currentTimeMillis() + TIMEOUT;

        if (!lock(goodsId, String.valueOf(time))) {
            System.out.println("The current number or people is too large!");
        }else {
            //一大坨代码
            int age = userDao.findAge(1);
            age = age - 1;
            System.out.println("age:" + age);
            userDao.update(1, age);
            //解锁
            unLock(goodsId, String.valueOf(time));
        }
        }
    }
}
