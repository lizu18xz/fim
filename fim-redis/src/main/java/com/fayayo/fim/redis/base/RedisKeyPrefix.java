package com.fayayo.fim.redis.base;

/**
 * @author dalizu on 2019/1/3.
 * @version v1.0
 * @desc
 */
public interface RedisKeyPrefix {

    int expireSeconds();

    String getPrefix();

}
