package com.atguigu.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.Set;

public class RedisTest_01 {

    public static void main(String[] args) {
        //TODO 需要注意的是企业中不会每次都new
        // 虽然 redis.conf配置文件中 连接limit为10000
        // 因为 redis是非常快的，但是建立连接是这里的瓶颈，拖慢了整个性能，
        //  所以引入    连接池
        Jedis jedis = RedisTest_01_RedisUtil.getJedisFromPool();


        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }
        Set<Tuple> z1 = jedis.zrevrangeWithScores("z1", 0, -1);
        System.out.println("*******");
        for (Tuple tuple : z1) {
            System.out.println(tuple.getElement()+":"+tuple.getScore());
        }

        //此处的colse并不是真正的关闭，而是自动将连接还回连接池
        jedis.close();
    }
}

