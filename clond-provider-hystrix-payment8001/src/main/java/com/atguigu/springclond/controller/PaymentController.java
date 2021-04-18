package com.atguigu.springclond.controller;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springclond.PaymentHystrixMain8001;
import com.atguigu.springclond.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * auther win
 * create 2021/3/15 0015 21:25
 **/
@RestController
@DefaultProperties(defaultFallback = "fallback_global")
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String port;

    @GetMapping("payment/ok/{id}")
    public String payment_ok(@PathVariable("id") Integer id){
        return paymentService.payemnt_ok(id)+"server.port: "+port;
    }

    @GetMapping("payment/timeout/{id}")
    @HystrixCommand
    /*@HystrixCommand(fallbackMethod = "payment_TimeoutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })*/
    public String payment_timeout(@PathVariable("id") Integer id){
       /* int timenum = 5;
        try {
            TimeUnit.SECONDS.sleep(timenum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        int a = 10/0;
        return paymentService.payemnt_timeout(id)+"server.port: "+port;
    }

    public String payment_TimeoutHandler(Integer id){
        return "线程池:"+Thread.currentThread().getName()+"payment_TimeoutHandler "+id+" 哈哈^_^";
    }

    //global fallback
    public String fallback_global(){
        return "系统繁忙,请稍后再试";
    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_handler",commandProperties = {
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60")
    })
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Long id){
            if (id<0){
                throw new RuntimeException("*****id 不能为负数");
            }
        String uuid = IdUtil.simpleUUID();
            return Thread.currentThread().getName()+"\t"+"调用成功,流水号:"+uuid;
    }

    public String paymentCircuitBreaker_handler(@PathVariable("id") Long id){
        return "id 不能为负数,请稍后再试! id"+id;
    }
}
