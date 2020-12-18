package com.atguigu.redis;

import redis.clients.jedis.Jedis;

import java.util.Set;

public class RedisTest_02 {

    public static void main(String[] args) {
        //获得哨兵池连接
        Jedis jedis = RedisTest_02_RedisSentinelUtil.getJedisFromSentinel();

        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }


        jedis.close();
    }
}

