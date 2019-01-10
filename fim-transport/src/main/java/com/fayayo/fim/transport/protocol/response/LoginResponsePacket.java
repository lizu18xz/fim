package com.fayayo.fim.transport.protocol.response;

import com.fayayo.fim.transport.protocol.Packet;
import com.fayayo.fim.transport.protocol.command.Command;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dalizu on 2019/1/10.
 * @version v1.0
 * @desc
 */
@Getter
@Setter
public class LoginResponsePacket extends Packet {

    private String userId;

    private String username;

    private boolean success;

    private String reason;

    @Override
    protected Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }

}
