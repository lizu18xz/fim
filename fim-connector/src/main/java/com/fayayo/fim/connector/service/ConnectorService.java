package com.fayayo.fim.connector.service;

import com.fayayo.fim.common.core.SendToUserRequest;

/**
 * @author dalizu on 2019/1/14.
 * @version v1.0
 * @desc
 */
public interface ConnectorService {

    void userChat(SendToUserRequest sendToUserRequest);

}
