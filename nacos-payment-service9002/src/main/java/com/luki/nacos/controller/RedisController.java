package com.luki.nacos.controller;

import com.atguigu.springclond.entities.CommonResult;
import com.atguigu.springclond.entities.Customer;
import com.google.common.base.Charsets;
import com.google.common.hash.Funnel;
import com.luki.nacos.service.CustomerService;
import com.luki.nacos.util.BloomFilterHelper;
import com.luki.nacos.util.RedisBloomFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * auther win
 * create 2021/5/4 0004 19:29
 **/
@RestController
@Slf4j
@RefreshScope
public class RedisController {
    @Resource
    private RedisTemplate<Object, Object> redis;
    @Resource
    private CustomerService service;
    @Resource
    private RedisBloomFilter redisBloomFilter;
    @Value("${server.port}")
    private Integer port;


    BloomFilterHelper<String> myBloomFilterHelper = new BloomFilterHelper<>(
            (Funnel<String>) (from, into) -> into.putString(from, Charsets.UTF_8).putString(from, Charsets.UTF_8), 150000, 0.00001);

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
            boolean isExist = redisBloomFilter.includeByBloomFilter(myBloomFilterHelper, "num_existed_bloom", String.valueOf(id));
            if (isExist) {
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
                result.setMessage("查询成功,查询端口:"+port);
                result.setDate(cus);
            } else {
                result.setCode(200);
                result.setMessage("查询数据不存,查询端口:"+port);
                result.setDate(null);
            }
            result.setIsExist(isExist);
            log.info("queryCustomer result:" + result);
        } catch (Exception e) {
            result.setCode(400);
            result.setMessage("查询异常,查询端口:"+port);
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
        try {
            Customer o = (Customer) redis.opsForValue().get(customer.getCus_num());
            if (o != null) {
                result.setCode(2001);
            } else {
                o = service.queryCustomer(customer);
                if (o != null) {
                    result.setCode(2001);
                } else {
                    int count = service.addCustomer(customer);
                    if (count > 0) {
                        redisBloomFilter.addByBloomFilter(myBloomFilterHelper, "num_existed_bloom", customer.getCus_num() + "");
                        result.setCode(200);
                        result.setMessage("新增成功");
                    }
                }
            }
        } catch (Exception e) {
            result.setCode(400);
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("update")
    public CommonResult updateCustomer(@RequestBody Customer customer) {
        CommonResult<Customer> result = new CommonResult<>();
        try {
            int count = service.updateCustomer(customer);
            if (count > 0) {
                result.setCode(200);
                result.setMessage("更新成功");
                redis.delete(customer.getCus_num());
            } else {
                result.setCode(201);
                result.setMessage("更新的数据不存在");
            }
        } catch (Exception e) {
            result.setCode(400);
            result.setMessage("发生异常");
            e.printStackTrace();
        }
        return result;
    }
    @RequestMapping("add/{cus_num}")
    public CommonResult addRedisBloomFilter(@PathVariable("cus_num") String cus_num){
        CommonResult<Customer> result = new CommonResult<>();
        try {
            redisBloomFilter.addByBloomFilter(myBloomFilterHelper, "num_existed_bloom", cus_num);
            result.setCode(200);
        } catch (Exception e) {
            result.setCode(400);
            e.printStackTrace();
        }
        return result;
    }
}
