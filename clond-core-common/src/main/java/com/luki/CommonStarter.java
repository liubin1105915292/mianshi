package com.luki;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * auther win
 * create 2021/5/13 0013 21:16
 **/
@SpringBootApplication
@MapperScan("com.luki.mapper")
@EnableAsync
public class CommonStarter {
    public static void main(String[] args) {
        SpringApplication.run(CommonStarter.class);
    }
}
