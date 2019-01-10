package com.fayayo.fim.transport.Attributes;

import com.fayayo.fim.transport.session.Session;
import io.netty.util.AttributeKey;

/**
 * @author dalizu on 2019/1/10.
 * @version v1.0
 * @desc
 */
public interface Attributes {

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

}
