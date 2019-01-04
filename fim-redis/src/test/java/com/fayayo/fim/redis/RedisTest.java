package com.fayayo.fim.redis;

import com.fayayo.fim.redis.base.BasePrefix;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dalizu on 2019/1/3.
 * @version v1.0
 * @desc
 */
public class RedisTest {


    public static void main(String[] args) {

        //不会报错
        ShardedJedisPool shardedJedisPool= shardedJedisPool();

        //具体获取连接的时候才会报错
        RedisTemplate redisTemplate=new RedisTemplate(shardedJedisPool);

        BasePrefix basePrefix=new BasePrefix(30,"abc");
        redisTemplate.set(basePrefix,"abb","134");


        System.out.println(redisTemplate.get(basePrefix,"abb",String.class));

        shardedJedisPool.close();
    }

    public static ShardedJedisPool shardedJedisPool(){

        //配置连接池
        JedisPoolConfig poolConfig=new JedisPoolConfig();
        //连接耗尽的时候是否阻塞，false会报错，true阻塞直到超时
        poolConfig.setBlockWhenExhausted(true);

        JedisShardInfo info1=new JedisShardInfo("192.168.88.129",6379,3*1000);
        info1.setPassword("root123");

        List<JedisShardInfo> jedisShardInfoList=new ArrayList<JedisShardInfo>();
        jedisShardInfoList.add(info1);

        return new ShardedJedisPool(poolConfig,jedisShardInfoList, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);

    }


}
