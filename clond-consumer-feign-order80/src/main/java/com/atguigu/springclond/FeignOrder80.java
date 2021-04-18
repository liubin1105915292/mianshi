package com.atguigu.springclond;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * auther win
 * create 2021/3/14 0014 11:14
 **/
@SpringBootApplication
@EnableFeignClients
public class FeignOrder80 {
    public static void main(String[] args) {
        SpringApplication.run(FeignOrder80.class);
    }
}
