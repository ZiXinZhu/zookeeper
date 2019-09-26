package com.zzx.consumer.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServer {

    @Autowired
    RestTemplate template;

    public String getUser() {
        return template.getForObject("http://zk-provider/find",String.class);
    }

}
