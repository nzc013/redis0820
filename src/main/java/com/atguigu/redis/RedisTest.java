package com.atguigu.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.Set;

public class RedisTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("hadoop102", 6379);


        Set<String> keys = jedis.keys("*");
        //快捷键 建立 增强for循环
        //直接输入：iter
        for (String key : keys) {
            System.out.println(key);
        }
        Set<Tuple> z1 = jedis.zrevrangeWithScores("z1", 0, -1);
        System.out.println("*******");
        for (Tuple tuple : z1) {
            System.out.println(tuple.getElement()+":"+tuple.getScore());
        }
        jedis.close();
    }
}

