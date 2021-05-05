package com.luki.redis.service;

import com.atguigu.springclond.entities.Customer;
import org.springframework.stereotype.Service;

/**
 * auther win
 * create 2021/5/5 0005 16:30
 **/
public interface CustomerService {
    /**
     *功能描述 新增客户
     * author liubin
     * date 2021/5/5 0005
     * param [customer]
     * return int
     */
    int addCustomer(Customer customer);

    /**
     *功能描述 查询客户
     * author liubin
     * date 2021/5/5 0005
     * param [customer]
     * return com.atguigu.springclond.entities.Customer
     */
    Customer queryCustomer(Customer customer);

    /**
     *功能描述 更新客户
     * author liubin
     * date 2021/5/5 0005
     * param [customer]
     * return int
     */
    int updateCustomer(Customer customer);
}
