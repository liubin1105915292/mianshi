package com.luki.nacos.dao;

import com.atguigu.springclond.entities.Customer;
import org.apache.ibatis.annotations.Mapper;

/**
 * auther win
 * create 2021/5/5 0005 15:24
 **/
@Mapper
public interface CustomerDao {
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
