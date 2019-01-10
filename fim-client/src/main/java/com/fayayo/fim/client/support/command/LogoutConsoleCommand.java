package com.fayayo.fim.client.support.command;

import com.fayayo.fim.transport.protocol.request.LogoutRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author dalizu on 2019/1/10.
 * @version v1.0
 * @desc
 */
public class LogoutConsoleCommand implements ConsoleCommand {


    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);
    }


}
