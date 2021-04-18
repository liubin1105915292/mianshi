package com.atguigu.springclond.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * auther win
 * create 2021/3/15 0015 21:10
 **/
@Service
public class PaymentService {
    public String payemnt_ok(Integer id){
        return "线程池: "+Thread.currentThread().getName()+" payment_ok,id: "+id+"\t"+"哈哈";
    }

    public String payemnt_timeout(Integer id){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池: "+Thread.currentThread().getName()+" payment_timeout,id: "+id+"\t"+"哈哈";
    }
}
