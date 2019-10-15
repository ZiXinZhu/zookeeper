package com.zzx.zookeeper_lock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ConfigurationProperties(prefix = "spring.cloud.zookeeper")
public class ZooKeeperLockController {
    private static final String IP="47.106.128.4:2181";
    @Autowired
    RedisTemplate<String, Object> redisTemplate;


    @GetMapping("/zk/sell")
    public String zookeeper_lock() throws Exception {


        for (int i = 0; i < 50; i++) {
            //创建zookeeper的客户端
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
            CuratorFramework client = CuratorFrameworkFactory.newClient(IP, retryPolicy);
            client.start();
            //创建分布式锁, 锁空间的根节点路径为/zk-provider/lock
            InterProcessMutex mutex = new InterProcessMutex(client, "/zk-provider/lock");
            mutex.acquire();
            //获得了锁, 进行业务流程
            int stock = Integer.parseInt(String.valueOf(redisTemplate.opsForValue().get("zzx")));
            if (stock > 0) {
                redisTemplate.opsForValue().set("zzx", (stock - 1) + "");
                System.out.println("结果:" + (stock - 1));
            }
            //完成业务流程, 释放锁
            mutex.release();
            //关闭客户端
            client.close();
        }
        return "success";
    }
}
