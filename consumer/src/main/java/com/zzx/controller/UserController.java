package com.zzx.controller;

import com.zzx.server.IUserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserController {


    @Autowired
    protected IUserServer server;

    @GetMapping("/get")
    public String getUser() {
        return server.find();
    }

    @GetMapping("/add")
    public List<String> getList() {
        return server.get();
    }
}
