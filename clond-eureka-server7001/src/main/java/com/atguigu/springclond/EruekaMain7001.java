package com.atguigu.springclond;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * auther win
 * create 2021/3/11 0011 20:58
 **/
@SpringBootApplication
@EnableEurekaServer
public class EruekaMain7001 {
    public static void main(String[] args) {
        SpringApplication.run(EruekaMain7001.class);
    }
}
