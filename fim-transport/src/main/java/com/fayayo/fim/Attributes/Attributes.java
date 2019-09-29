package com.fayayo.fim.Attributes;

import com.fayayo.fim.session.Session;
import io.netty.util.AttributeKey;

/**
 * @author dalizu on 2019/1/10.
 * @version v1.0
 * @desc
 */
public interface Attributes {

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

}
