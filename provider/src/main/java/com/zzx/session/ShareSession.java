package com.zzx.provider.session;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

@RestController
public class ShareSession {


    @RequestMapping("/session/set")
    public String setSession(HttpServletRequest servletRequest){
        servletRequest.getSession().setAttribute("zzx","qq");
        return "success";
    }
}
