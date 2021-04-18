package com.atguigu.springclond.service.impl;

import com.atguigu.springclond.dao.PaymentDao;
import com.atguigu.springclond.entities.Payment;
import com.atguigu.springclond.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *auther win
 *create 2021/3/6 0006 19:37
 **/
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao dao;

    @Override
    public int create(Payment payment) {
        return dao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return dao.getPaymentById(id);
    }
}
