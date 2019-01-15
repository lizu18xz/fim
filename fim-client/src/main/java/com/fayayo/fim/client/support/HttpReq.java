package com.fayayo.fim.client.support;

/**
 * @author dalizu on 2019/1/10.
 * @version v1.0
 * @desc 接口
 */
public class HttpReq {

    private static final String CONNECTOR_PREFIX="http://localhost:8080/fim";

    //用户登陆请求地址
    public static final String CONNECTOR_POST_LOGIN=CONNECTOR_PREFIX+"/user/login";


    //用户私聊地址
    public static final String CONNECTOR_POST_ONLINE_USER_CHAT=CONNECTOR_PREFIX+"/online/userChat";


    //广播地址
    public static final String CONNECTOR_POST_ONLINE_BROADCAST=CONNECTOR_PREFIX+"/online/broadcast";


    //群聊地址
    public static final String CONNECTOR_POST_ONLINE_GROUP_CHAT=CONNECTOR_PREFIX+"/online/groupChat";


}
