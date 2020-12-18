package com.atguigu.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.Set;

public class RedisTest_03 {

    public static void main(String[] args) {
        //获得哨兵池连接
        JedisCluster jedisCluster = RedisTest_03_RedisClusterUtil.getJedisCluster();
        //TODO　JedisCluster　不支持keys * 操作
        //Exception in thread "main" java.lang.IllegalArgumentException: JedisCluster only supports KEYS commands with patterns containing hash-tags ( curly-brackets enclosed strings )
        //	at redis.clients.jedis.JedisCluster.keys(JedisCluster.java:1387)
        //	at com.atguigu.redis.RedisTest_03.main(RedisTest_03.java:14)
   /*     Set<String> keys = jedisCluster.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }
*/
        jedisCluster.set("k13","v13");
        System.out.println(jedisCluster.get("k13"));


        //池子不能关闭
//        jedisCluster.close();
}
}

