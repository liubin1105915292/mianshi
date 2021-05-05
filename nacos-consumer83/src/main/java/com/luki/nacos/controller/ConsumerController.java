package com.luki.nacos.controller;

import com.atguigu.springclond.entities.CommonResult;
import com.atguigu.springclond.entities.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * auther win
 * create 2021/5/5 0005 23:24
 **/
@RestController
@Slf4j
public class ConsumerController {
    @Resource
    private RestTemplate restTemplate;
    @Value("${service-url.nacos-user-service}")
    private String serverUrl;

    @RequestMapping("query")
    public CommonResult queryCustomer(@RequestBody Customer customer) {
        CommonResult result = new CommonResult();
        try {
            //result = restTemplate.getForObject(serverUrl + "/query", CommonResult.class, customer);
             result = restTemplate.postForObject(serverUrl + "/query", customer, CommonResult.class);
        } catch (RestClientException e) {
            result.setCode(400);
            e.printStackTrace();
        }
        log.info("查询结果result:"+result);
        return result;
    }

    @RequestMapping("add/{cus_num}")
    public CommonResult addRedisBloomFilter(@PathVariable("cus_num") String cus_num){
        CommonResult resutl = restTemplate.getForObject(serverUrl + "/add/" + cus_num, CommonResult.class);
        return resutl;
    }
}
