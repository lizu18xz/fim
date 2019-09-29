package com.fayayo.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
 * @desc Redis Audo Config
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(RedisConfig.class)
public class RedisAutoConfiguration {


    @Autowired
    private RedisConfig redisConfig;

    @Bean
    public RedisTemplate redisTemplate(){

        log.info("support redisPool success");
        return new RedisTemplate(shardedJedisPool());
    }

    public ShardedJedisPool shardedJedisPool(){

        //配置连接池
        JedisPoolConfig poolConfig=new JedisPoolConfig();
        poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
        poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
        poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait());
        //连接耗尽的时候是否阻塞，false会报错，true阻塞直到超时
        poolConfig.setBlockWhenExhausted(true);
        log.info("redis host:{},port:{}",redisConfig.getHost(),redisConfig.getPort());
        JedisShardInfo info1=new JedisShardInfo(redisConfig.getHost(),redisConfig.getPort(),redisConfig.getTimeout()*1000);

        if("true".equals(redisConfig.getAuth())){
            info1.setPassword(redisConfig.getPassword());
        }

        List<JedisShardInfo> jedisShardInfoList=new ArrayList<JedisShardInfo>();
        jedisShardInfoList.add(info1);

        return new ShardedJedisPool(poolConfig,jedisShardInfoList, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);

    }

}
