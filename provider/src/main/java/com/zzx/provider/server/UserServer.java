package com.zzx.provider.server;


import com.zzx.provider.dao.UserDao;
import com.zzx.provider.po.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServer {

    @Autowired
    UserDao userDao;
    public UserPO findById(int id){
        if(id==0){
            return null;
        }
        return userDao.findById(id);
    }
}
