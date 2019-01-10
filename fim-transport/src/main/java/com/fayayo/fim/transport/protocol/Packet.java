package com.fayayo.fim.transport.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import com.fayayo.fim.transport.protocol.command.Command;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author dalizu on 2019/1/10.
 * @version v1.0
 * @desc 协议
 */
@Getter
@Setter
public abstract class Packet implements Serializable {

    @JSONField(deserialize = false, serialize = false)
    private Byte version=1;

    @JSONField(serialize = false)
    protected abstract Byte getCommand();

}
