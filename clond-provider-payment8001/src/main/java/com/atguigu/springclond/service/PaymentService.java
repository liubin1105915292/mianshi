package com.atguigu.springclond.service;

import com.atguigu.springclond.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * auther win
 * create 2021/3/6 0006 19:36
 **/
public interface PaymentService {
    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
