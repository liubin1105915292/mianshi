package com.luki.nacos.controller;

import com.luki.nacos.util.IdGeneratorSnowflake;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * auther win
 * create 2021/5/6 0006 20:14
 **/
@RestController
@RefreshScope  //支持nacos动态刷新功能
public class ConfigClientController {
    @Value("${config.info}")
    private String configInfo;
    @Resource
    private IdGeneratorSnowflake snowflake;

    @RequestMapping("getInfo")
    public String getConfigInfo(){
        return  configInfo;
    }

    @RequestMapping("getId")
    public String getIDBySnowflake(){
        ExecutorService factory = Executors.newFixedThreadPool(5);
        for(int i=1;i<=10;i++){
            factory.submit(()->{
                long l = snowflake.snowflakeId();
                System.out.println(l);
            });
        }
        factory.shutdown();
        return "ok";
    }
}
