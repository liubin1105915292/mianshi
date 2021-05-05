package com.redis.luki;

import redis.clients.jedis.Jedis;

/**
 * auther win
 * create 2021/4/11 0011 16:46
 **/
public class ListTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.0.109", 6379);
        Long list1 = jedis.lpush("list1", "1", "2", "5");
        System.out.println(jedis.lrange("list1",0,-1));
        Long list11 = jedis.lpush("list1", "6", "7");
        System.out.println(jedis.lrange("list1",0,-1));
        jedis.del("list1");
        System.out.println(jedis.lrange("list1",0,-1));
        jedis.close();
    }
}
