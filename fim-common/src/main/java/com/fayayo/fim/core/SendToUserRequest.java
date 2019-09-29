package com.fayayo.fim.core;

import lombok.Getter;
import lombok.Setter;

/**
 * @author dalizu on 2019/1/14.
 * @version v1.0
 * @desc
 */
@Getter
@Setter
public class SendToUserRequest extends Request{

    private String userId;

    private String toUserId;

    private String msg;

    @Override
    public String toString() {
        return "{" +
                "userId='" + userId + '\'' +
                ", toUserId='" + toUserId + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
