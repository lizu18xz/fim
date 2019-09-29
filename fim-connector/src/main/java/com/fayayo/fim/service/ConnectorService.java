package com.fayayo.fim.service;

import com.fayayo.fim.core.SendToUserRequest;

/**
 * @author dalizu on 2019/1/14.
 * @version v1.0
 * @desc
 */
public interface ConnectorService {

    void userChat(SendToUserRequest sendToUserRequest);

    void broadcast(SendToUserRequest sendToUserRequest);

}
