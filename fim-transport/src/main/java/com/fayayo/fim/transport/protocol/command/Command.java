package com.fayayo.fim.transport.protocol.command;

/**
 * @author dalizu on 2019/1/10.
 * @version v1.0
 * @desc 指令
 */
public interface Command {


    //登陆
    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    //登出
    Byte LOGOUT_REQUEST = 3;

    Byte LOGOUT_RESPONSE = 4;

    //消息发送
    Byte MESSAGE_REQUEST = 5;

    Byte MESSAGE_RESPONSE = 6;


}
