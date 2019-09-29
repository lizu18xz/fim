package com.fayayo.redis;

import com.fayayo.fim.closable.ShutDownHook;
import com.fayayo.redis.base.RedisKeyPrefix;
import com.fayayo.redis.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author dalizu on 2019/1/3.
 * @version v1.0
 * @desc 提供Redis工具类
 */
@Slf4j
public class RedisTemplate implements Closeable{


    private ShardedJedisPool shardedJedisPool;

    public RedisTemplate(ShardedJedisPool shardedJedisPool) {
        this.shardedJedisPool = shardedJedisPool;
        ShutDownHook.registerShutdownHook(this);
    }

    public <T> T get(RedisKeyPrefix prefix, String key, Class<T> clazz) {

        //从连接池获取连接
        ShardedJedis shardedJedis = this.shardedJedisPool.getResource();
        try {

            String realKey = prefix.getPrefix() + key;

            String str = shardedJedis.get(realKey);

            T t = JsonMapper.string2Obj(str, clazz);

            return t;

        } catch (Exception e) {
            log.error("redis 获取数据异常:{}", e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            returnToPool(shardedJedis);
        }
    }


    public <T> boolean set(RedisKeyPrefix prefix, String key, T value) {
        //从连接池获取连接
        ShardedJedis shardedJedis = this.shardedJedisPool.getResource();
        try {

            String str = JsonMapper.obj2String(value);
            if (str == null || str.length() <= 0) {
                return false;
            }
            String realKey = prefix.getPrefix() + key;

            int seconds = prefix.expireSeconds();
            if (seconds <= 0) {
                shardedJedis.set(realKey, str);
            } else {
                shardedJedis.setex(realKey, seconds, str);  //带过期时间的缓存
            }

            return true;

        } catch (Exception e) {
            log.error("redis 设置数据异常:{}", e.getMessage());
            e.printStackTrace();
            return false;
        } finally {
            returnToPool(shardedJedis);
        }
    }

    public boolean delete(RedisKeyPrefix prefix, String key) {

        ShardedJedis shardedJedis = null;
        try {

            shardedJedis = shardedJedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            long ret = shardedJedis.del(realKey);

            return ret > 0;
        } catch (Exception e) {
            return false;
        } finally {
            returnToPool(shardedJedis);
        }
    }

    public <T> Long incr(RedisKeyPrefix prefix, String key) {

        ShardedJedis shardedJedis = null;
        try {

            shardedJedis = shardedJedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return shardedJedis.incr(realKey);

        } catch (Exception e) {
            return null;
        } finally {
            returnToPool(shardedJedis);
        }

    }


    private void returnToPool(ShardedJedis shardedJedis) {
        if (shardedJedis != null) {
            shardedJedis.close();
        }
    }

    @Override
    public void close() throws IOException {
        log.info("jedisPool close()");
        shardedJedisPool.close();
    }
}
