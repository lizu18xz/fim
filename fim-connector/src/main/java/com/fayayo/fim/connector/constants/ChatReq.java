package com.fayayo.fim.connector.constants;

/**
 * @author dalizu on 2019/1/15.
 * @version v1.0
 * @desc chat服务地址
 */
public class ChatReq {

    private static final String CHAT_PREFIX="http://%s/chat";

    //私聊
    public static final String CHAT_POST_SEND_TO_USER=CHAT_PREFIX+"/online/sendToUser";


}
