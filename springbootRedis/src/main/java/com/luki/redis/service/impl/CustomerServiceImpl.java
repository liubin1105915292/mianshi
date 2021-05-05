package com.luki.redis.service.impl;

import com.atguigu.springclond.entities.Customer;
import com.luki.redis.dao.CustomerDao;
import com.luki.redis.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * auther win
 * create 2021/5/5 0005 16:31
 **/
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao dao;

    @Override
    public int addCustomer(Customer customer) {
        return dao.addCustomer(customer);
    }

    @Override
    public Customer queryCustomer(Customer customer) {
        return dao.queryCustomer(customer);
    }

    @Override
    public int updateCustomer(Customer customer) {
        return dao.updateCustomer(customer);
    }
}
