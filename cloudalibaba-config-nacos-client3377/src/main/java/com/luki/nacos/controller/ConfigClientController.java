package com.luki.nacos.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * auther win
 * create 2021/5/6 0006 20:14
 **/
@RestController
@RefreshScope  //支持nacos动态刷新功能
public class ConfigClientController {
    @Value("${config.info}")
    private String configInfo;

    @RequestMapping("getInfo")
    public String getConfigInfo(){
        return  configInfo;
    }
}
