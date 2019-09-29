package com.fayayo.fim.protocol.request;

import com.fayayo.fim.protocol.Packet;
import com.fayayo.fim.protocol.command.Command;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dalizu on 2019/1/10.
 * @version v1.0
 * @desc
 */
@Getter
@Setter
public class LoginRequestPacket extends Packet {

    private String userId;

    private String username;

    @Override
    protected Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }

    @Override
    public String toString() {
        return "{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
