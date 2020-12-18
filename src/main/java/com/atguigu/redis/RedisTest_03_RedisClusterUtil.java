package com.atguigu.redis;

import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;


//TODO 集群


public class RedisTest_03_RedisClusterUtil {


    private static JedisCluster jedisCluster = null;

    public static JedisCluster getJedisCluster() {
        if (jedisCluster == null) {


            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(10); //最大可用连接数
            jedisPoolConfig.setMaxIdle(5); //最大闲置连接数
            jedisPoolConfig.setMinIdle(5); //最小闲置连接数
            jedisPoolConfig.setBlockWhenExhausted(true); //连接耗尽是否等待
            jedisPoolConfig.setMaxWaitMillis(2000); //等待时间
            jedisPoolConfig.setTestOnBorrow(true); //取连接的时候进行一下测试 ping pong

            Set<HostAndPort> hostAndPortSet = new HashSet<>();
            hostAndPortSet.add(new HostAndPort("hadoop102", 6390));
            hostAndPortSet.add(new HostAndPort("hadoop102", 6391));

            jedisCluster = new JedisCluster(hostAndPortSet, jedisPoolConfig);
            return jedisCluster;
        } else {
            return jedisCluster;
        }

    }

}

