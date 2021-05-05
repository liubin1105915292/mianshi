package com.luki.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * auther win
 * create 2021/5/5 0005 23:23
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class Consumer83 {
    public static void main(String[] args) {
        SpringApplication.run(Consumer83.class);
    }
}
