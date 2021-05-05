package com.luki.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * auther win
 * create 2021/5/4 0004 22:52
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class NacosApplication9001 {
    public static void main(String[] args) {
        SpringApplication.run(NacosApplication9001.class);
    }
}
