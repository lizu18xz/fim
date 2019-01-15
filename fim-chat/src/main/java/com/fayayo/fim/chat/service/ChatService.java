package com.fayayo.fim.chat.service;

import com.fayayo.fim.common.core.SendToUserRequest;

/**
 * @author dalizu on 2019/1/15.
 * @version v1.0
 * @desc
 */
public interface ChatService {

    void sendToUser(SendToUserRequest sendToUserRequest);

}
