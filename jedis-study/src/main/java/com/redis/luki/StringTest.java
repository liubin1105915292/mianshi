package com.redis.luki;

import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * auther win
 * create 2021/4/11 0011 15:31
 **/
public class StringTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.0.109",6379);
        Boolean b1 = jedis.exists("name");
        System.out.println(b1);
        String mset = jedis.mset("name", "张三", "age", "27");
        System.out.println(mset);
        List<String> mget = jedis.mget("name", "age");
        mget.forEach(System.out::println);
      //  Long name = jedis.del("name");
      //  System.out.println(name);
        Set<String> keys = jedis.keys("*");
        keys.forEach(System.out::println);
        jedis.close();
    }
}
