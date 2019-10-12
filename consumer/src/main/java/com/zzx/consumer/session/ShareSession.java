package com.zzx.consumer.session;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
public class ShareSession {


    @RequestMapping("/session/get")
    public String setSession(HttpServletRequest servletRequest){
        return String.valueOf(servletRequest.getSession().getAttribute("zzx"));
    }
}
