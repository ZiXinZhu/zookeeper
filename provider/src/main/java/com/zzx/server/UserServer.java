package com.zzx.server;


import com.zzx.dao.UserDao;
import com.zzx.po.UserPO;
import org.springframework.beans.factory.annotation.Autowired;


public class UserServer {

    @Autowired
    UserDao userDao;
    public UserPO findById(){
        return userDao.findById(1);
    }
}
