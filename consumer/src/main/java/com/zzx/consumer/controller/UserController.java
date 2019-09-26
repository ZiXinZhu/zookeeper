package com.zzx.consumer.controller;

import com.zzx.consumer.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @Autowired
    UserServer server;

    @GetMapping("/get")
    public String getUser() {
        return server.getUser();
    }
}
