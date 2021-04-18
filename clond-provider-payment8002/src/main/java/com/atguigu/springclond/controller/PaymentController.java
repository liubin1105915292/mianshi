package com.atguigu.springclond.controller;

import com.atguigu.springclond.entities.CommonResult;
import com.atguigu.springclond.entities.Payment;
import com.atguigu.springclond.service.PaymentService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * auther win
 * create 2021/3/6 0006 19:34
 **/
@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String port;

    @Resource
    private PaymentService service;
    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody  Payment payment){
        int result = service.create(payment);
        log.info("****插入结果:"+result);
        if (result>0){
            return new CommonResult(200,"插入成功");
        }else{
            return new CommonResult(400,"插入数据库失败");
        }
    }
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payemnt = service.getPaymentById(id);
        log.info("查询结果payemnt:"+payemnt);
        if (payemnt!=null){
            return new CommonResult<>(200,"查询成功,serverPort:"+port,payemnt);
        }else{
            return new CommonResult<>(400,"查询失败");
        }
    }

}

