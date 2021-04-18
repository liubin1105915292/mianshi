package com.atguigu.springclond.controller;

import com.atguigu.springclond.entities.CommonResult;
import com.atguigu.springclond.entities.Payment;
import com.atguigu.springclond.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * auther win
 * create 2021/3/14 0014 11:22
 **/
@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        CommonResult<Payment> payment = paymentFeignService.getPaymentById(id);
        log.info("feign payemnt:"+payment);
        return payment;
    }

    @GetMapping("/consumer/payment/feigin/timeout")
    public String paymentFeignTimeOut(){
        //openfeign-ribbon 客户端一般默认一秒
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }
}
