package com.atguigu.springclond.service;

import com.atguigu.springclond.entities.CommonResult;
import com.atguigu.springclond.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * auther win
 * create 2021/3/14 0014 11:17
 **/
@Component
@FeignClient(value = "CLOND-PROVIDER-PAYMENT")
public interface PaymentFeignService {
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping("/payment/feigin/timeout")
    public String paymentFeignTimeOut();
}
