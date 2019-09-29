package com.fayayo.fim.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.fayayo.fim.serialize.Serializer;
import com.fayayo.fim.serialize.SerializerAlogrithm;

/**
 * @author dalizu on 2018/10/11.
 * @version v1.0
 * @desc 对于嵌套场景无法支持
 */
public class JSONSerializer implements Serializer {

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }

    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlogrithm.JSON;
    }
}
