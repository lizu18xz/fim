package com.fayayo.fim.redis.key;

import com.fayayo.fim.redis.base.BasePrefix;

/**
 * @author dalizu on 2019/1/9.
 * @version v1.0
 * @desc
 */
public class TestKey extends BasePrefix {

    private static final int expiredSeconds=7200;

    private TestKey(String prefix) {
        super(expiredSeconds, prefix);
    }

    public static TestKey testKey=new TestKey("test_");


}
