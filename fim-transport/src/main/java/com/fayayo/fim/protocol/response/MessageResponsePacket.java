package com.fayayo.fim.protocol.response;

import com.fayayo.fim.protocol.Packet;
import com.fayayo.fim.protocol.command.Command;
import lombok.Getter;
import lombok.Setter;

/**
 * @author dalizu on 2019/1/15.
 * @version v1.0
 * @desc
 */
@Getter
@Setter
public class MessageResponsePacket extends Packet {


    private String fromUserId;//来自谁的消息

    private String message;

    public MessageResponsePacket(String fromUserId, String message) {
        this.fromUserId = fromUserId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {

        return Command.MESSAGE_RESPONSE;
    }

}
