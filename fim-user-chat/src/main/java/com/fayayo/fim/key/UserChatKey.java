package com.fayayo.fim.key;

import com.fayayo.redis.base.BasePrefix;

/**
 * @author dalizu on 2019/1/8.
 * @version v1.0
 * @desc
 */
public class UserChatKey extends BasePrefix{


    private UserChatKey(String prefix) {
        super(prefix);
    }

    //登陆用户和chat服务器的关系  key,保持持久性,用户下线后才删除
    public static UserChatKey userChatRel=new UserChatKey("ucr_");


}
