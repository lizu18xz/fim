package com.fayayo.fim.service;

import com.fayayo.fim.core.URL;

/**
 * @author dalizu on 2019/9/29.
 * @version v1.0
 * @desc
 */
public interface UserChatService {

    //保存用户和chat服务的对应关系,user_key_id:address
    boolean bindUserChat(String userId, URL url);

    //解除绑定
    boolean unbindUserChat(String userId);

    //获取
    URL getChatByUser(String userId);

}
