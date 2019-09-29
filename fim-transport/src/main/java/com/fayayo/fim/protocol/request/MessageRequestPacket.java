package com.fayayo.fim.protocol.request;

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
public class MessageRequestPacket extends Packet {

    private String toUserId;//指定接收者的id

    private String message;

    private String fromUserId;

    public MessageRequestPacket(String toUserId, String message, String fromUserId) {
        this.toUserId = toUserId;
        this.message = message;
        this.fromUserId = fromUserId;
    }

    @Override
    protected Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
