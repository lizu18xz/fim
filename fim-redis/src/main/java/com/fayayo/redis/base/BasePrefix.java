package com.fayayo.redis.base;

/**
 * @author dalizu on 2019/1/3.
 * @version v1.0
 * @desc
 */
public abstract class BasePrefix implements RedisKeyPrefix {

    private int expiredSeconds;

    private String prefix;


    public BasePrefix(int expiredSeconds, String prefix) {
        this.expiredSeconds = expiredSeconds;
        this.prefix = prefix;
    }

    public BasePrefix(String prefix) {
        this(0,prefix);
    }

    @Override
    public int expireSeconds() {
        return this.expiredSeconds;
    }

    //设置key值,不能重复
    @Override
    public String getPrefix() {
        String className=getClass().getSimpleName();
        return className+":"+prefix;
    }

}
