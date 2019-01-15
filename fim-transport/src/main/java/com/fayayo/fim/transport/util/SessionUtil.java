package com.fayayo.fim.transport.util;

import com.fayayo.fim.transport.Attributes.Attributes;
import com.fayayo.fim.transport.session.Session;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dalizu on 2019/1/10.
 * @version v1.0
 * @desc 保存每个服务器中 建立连接的用户信息
 */
@Slf4j
public class SessionUtil {

    private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();


    //绑定登陆信息
    public static void bindSession(Session session, Channel channel) {
        userIdChannelMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            Session session = getSession(channel);
            userIdChannelMap.remove(session.getUserId());
            channel.attr(Attributes.SESSION).set(null);
            log.info(session + " 退出登录!");
        }
    }

    public static boolean hasLogin(Channel channel) {

        return getSession(channel) != null;
    }

    public static Session getSession(Channel channel) {

        if(channel==null){
            return null;
        }
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId) {

        return userIdChannelMap.get(userId);
    }
}
