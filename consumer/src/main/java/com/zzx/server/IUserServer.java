package com.zzx.server;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Service
@FeignClient("zk-provider")
public interface IUserServer {


    @GetMapping("/find")
    String findById() ;

    @GetMapping("/list")
    List<String> getList();
}
