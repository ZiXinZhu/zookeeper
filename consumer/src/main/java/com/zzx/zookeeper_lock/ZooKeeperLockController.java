package com.zzx.zookeeper_lock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZooKeeperLockController {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;


    @GetMapping("/zk/sell")
    public String zookeeper_lock(){
        //创建zookeeper的客户端
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("47.106.128.4:2181,120.77.222.219:2181,123.207.231.158:2181", retryPolicy);
        client.start();
        for(int i=0;i<50;i++) {
            //创建分布式锁, 锁空间的根节点路径为/curator/lock
            InterProcessMutex mutex = new InterProcessMutex(client, "/curator/lock");
            try {
                mutex.acquire();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //获得了锁, 进行业务流程
            int stock = Integer.parseInt(String.valueOf(redisTemplate.opsForValue().get("zzx")));
            if(stock > 0){
                redisTemplate.opsForValue().set("zzx",(stock-1)+"");
                System.out.println("结果:"+(stock-1));
            }
            //完成业务流程, 释放锁
            try {
                mutex.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //关闭客户端
            client.close();
        }
        return "success";
    }
}
