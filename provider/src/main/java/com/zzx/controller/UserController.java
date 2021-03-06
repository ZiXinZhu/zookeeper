package com.zzx.controller;

import com.zzx.po.UserPO;
import com.zzx.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserServer userServer;

    @GetMapping("/find")
    public UserPO findById(){
        return userServer.findById();
    }


    @GetMapping("/list")
    public List<String > getList() {
        return new ArrayList<String>() {
            {
                add("lz");
            }
        };
    }
}
