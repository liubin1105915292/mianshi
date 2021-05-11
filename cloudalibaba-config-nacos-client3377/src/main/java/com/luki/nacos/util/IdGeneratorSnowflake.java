package com.luki.nacos.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**雪花算法
*功能描述
* author liubin
* date
* param
* return
*/
@Component
@Slf4j
public class IdGeneratorSnowflake {
    private long workerId = 0;
    private long datacenterId = 0;
    private Snowflake snowflake = IdUtil.createSnowflake(workerId,datacenterId);

    @PostConstruct
    /*
    *功能描述 初始化
    * author liubin
    * date 2021/5/11 0011
    * param []
    * return void
    */
    public void init(){
        try {
            System.out.println("*******初始化*********");
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            log.info("当前机器的workedId:{"+workerId+"}");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("当前机器的workedId获取失败",e);
            workerId = NetUtil.getLocalhostStr().hashCode();
        }
    }

    public synchronized long snowflakeId(){
        return snowflake.nextId();
    }
    public synchronized long snowflakeId(long workerId,long datacenterId){
        Snowflake snowflake = IdUtil.createSnowflake(workerId,datacenterId);
        return snowflake.nextId();
    }

    public static void main(String[] args) {
        IdGeneratorSnowflake idGeneratorSnowflake = new IdGeneratorSnowflake();
        System.out.println(idGeneratorSnowflake.snowflakeId());
    }
}
