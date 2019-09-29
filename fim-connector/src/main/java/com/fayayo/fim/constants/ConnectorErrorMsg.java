package com.fayayo.fim.constants;

import com.fayayo.fim.exception.FimErrorMsg;

/**
 * @author dalizu on 2019/1/8.
 * @version v1.0
 * @desc
 */
public class ConnectorErrorMsg {

    public static final FimErrorMsg CHAT_SERVICE_NOFOUND = new FimErrorMsg(404,  500,"chat service unfound");

    public static final FimErrorMsg REDIS_ERROR = new FimErrorMsg(500,  501,"redis error");


    public static final FimErrorMsg USER_OFFLINE = new FimErrorMsg(500,  601,"用户下线了");



}
