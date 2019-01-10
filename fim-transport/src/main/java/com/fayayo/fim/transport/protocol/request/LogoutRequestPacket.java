package com.fayayo.fim.transport.protocol.request;

import com.fayayo.fim.transport.protocol.Packet;
import com.fayayo.fim.transport.protocol.command.Command;

/**
 * @author dalizu on 2019/1/10.
 * @version v1.0
 * @desc
 */
public class LogoutRequestPacket extends Packet {


    @Override
    protected Byte getCommand() {
        return Command.LOGOUT_REQUEST;
    }
}
