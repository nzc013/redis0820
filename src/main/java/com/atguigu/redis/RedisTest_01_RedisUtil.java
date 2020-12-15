package com.atguigu.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Tuple;

import java.util.Set;

public class RedisTest_01_RedisUtil {

    //开辟连接池的目的就是：当你需要连接时，直接从池子里面拿，（连接已经以前准备好了）
    //你用完之后，还回去就行了。

    private static JedisPool jedisPool=null;

    public static  Jedis getJedisFromPool(){
        //创建连接池
        if(jedisPool==null){
            JedisPoolConfig jedisPoolConfig =new JedisPoolConfig();
            //最大可用连接数
            //  请求超过会报错，或者阻塞
            jedisPoolConfig.setMaxTotal(10);
            //最大闲置连接数
            jedisPoolConfig.setMaxIdle(5);
            //最小闲置连接数
            jedisPoolConfig.setMinIdle(5);
            //连接耗尽是否等待
            jedisPoolConfig.setBlockWhenExhausted(true);
            //等待时间
            jedisPoolConfig.setMaxWaitMillis(2000);
            //取连接的时候进行一下测试 ping pong
            // 保证从连接池借过来的连接是好的，如果不测的话，连接有问题，这次的操作就会失败
            //从而导致用户体验下降
            //所以借过来的时候，测试一下，确保连接是好的，从而提高用户体验
            jedisPoolConfig.setTestOnBorrow(true);

            jedisPool=new JedisPool(jedisPoolConfig,"192.168.219.102", 6379 );
            return jedisPool.getResource();
        }else{
            //从连接池中得到连接
            return jedisPool.getResource();
        }
    }
}

