package com.luki.redis.controller;

import com.atguigu.springclond.entities.CommonResult;
import com.atguigu.springclond.entities.Customer;
import com.luki.redis.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * auther win
 * create 2021/5/4 0004 19:29
 **/
@RestController
@Slf4j
public class RedisController {
    @Autowired
    private RedisTemplate<Object, Object> redis;
    @Autowired
    private CustomerService service;

    @PostMapping("setValue/{key}/{value}")
    public String setKey(@PathVariable("key") String key, @PathVariable("value") String value) {
        try {
           /* SessionCallback sessionCallback = new SessionCallback() {
                @Override
                public Object execute(RedisOperations redisOperations) throws DataAccessException {

                    try {
                        redisOperations.multi();
                        redisOperations.opsForValue().set(key, value);
                        return  redisOperations.exec();
                    } catch (Exception e) {
                       e.printStackTrace();
                    }
                }
            };*/
            //redis.execute(sessionCallback);

        } catch (Exception e) {
            redis.discard();
            log.error("发生异常:", e);
        }
        return "SUCCESS";
    }


    @RequestMapping("query")
    public CommonResult queryCustomer(@RequestBody Customer customer) {
        log.info("queryCustomer customer:" + customer);
        CommonResult<Customer> result = new CommonResult<>();
        try {
            Integer id = customer.getCus_num();
            Customer cus = (Customer) redis.opsForValue().get(id);
            if (cus == null) {
                log.info("*******************调用sql*******************");
                cus = service.queryCustomer(customer);
                if (cus != null) {
                    //放入缓存
                    redis.opsForValue().set(id, cus);
                }
            }
            result.setCode(200);
            result.setMessage("查询成功");
            result.setDate(cus);
            log.info("queryCustomer result:"+result);
        } catch (Exception e) {
            result.setCode(400);
            result.setMessage("查询异常");
            e.printStackTrace();
        }
        return result;

    }

    /**
     * 功能描述
     * author liubin
     * date 2021/5/5 0005
     * param [customer]
     * return com.atguigu.springclond.entities.CommonResult
     */
    @RequestMapping("add")
    public CommonResult addCustomer(@RequestBody Customer customer) {
        CommonResult<Customer> result = new CommonResult<>();
        return result;
    }

    @RequestMapping("update")
    public CommonResult updateCustomer(@RequestBody Customer customer) {
        CommonResult<Customer> result = new CommonResult<>();

        return result;
    }
}
