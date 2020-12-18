package com.atguigu.redis;

import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;


//TODO RedisTest RedisTest01 类都是通过单点的方式获得连接
// 此时使用的是集群模式
// 创建哨兵池

public class RedisTest_02_RedisSentinelUtil {

    private static JedisSentinelPool jedisSentinelPool = null;

    public static Jedis getJedisFromSentinel() {
        if (jedisSentinelPool == null) {

            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(10); //最大可用连接数
            jedisPoolConfig.setMaxIdle(5); //最大闲置连接数
            jedisPoolConfig.setMinIdle(5); //最小闲置连接数
            jedisPoolConfig.setBlockWhenExhausted(true); //连接耗尽是否等待
            jedisPoolConfig.setMaxWaitMillis(2000); //等待时间
            jedisPoolConfig.setTestOnBorrow(true); //取连接的时候进行一下测试 ping pong

            //哨兵集合：因为哨兵不只一个
            Set<String> sentinelSet = new HashSet<>();
            //将哨兵添加进set中：参数是哨兵的地址
            sentinelSet.add("192.168.219.102:26379");
            jedisSentinelPool = new JedisSentinelPool("mymaster", sentinelSet, jedisPoolConfig);
            return jedisSentinelPool.getResource();
        } else {
            return jedisSentinelPool.getResource();
        }

    }

}

