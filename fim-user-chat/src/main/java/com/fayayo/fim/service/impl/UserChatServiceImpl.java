package com.fayayo.fim.service.impl;

import com.fayayo.fim.core.URL;
import com.fayayo.fim.key.UserChatKey;
import com.fayayo.fim.service.UserChatService;
import com.fayayo.redis.RedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dalizu on 2019/9/29.
 * @version v1.0
 * @desc 维持用户 和  chat 服务器之间的关系
 */
@Service
public class UserChatServiceImpl implements UserChatService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean bindUserChat(String userId, URL url) {
        boolean bind=redisTemplate.set(UserChatKey.userChatRel,userId,url);
        return bind;
    }

    @Override
    public boolean unbindUserChat(String userId) {

        boolean unbind=redisTemplate.delete(UserChatKey.userChatRel,userId);

        return unbind;
    }

    @Override
    public URL getChatByUser(String userId) {
        URL url=redisTemplate.get(UserChatKey.userChatRel,userId,URL.class);
        return url;
    }
}
