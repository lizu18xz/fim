package com.fayayo.fim.client.support.command;

import com.fayayo.fim.transport.protocol.request.LogoutRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author dalizu on 2019/1/10.
 * @version v1.0
 * @desc
 */
public class LogoutConsoleCommand implements ConsoleCommand{


    public void exec(Scanner scanner, Channel channel) {
        try {
            LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
            channel.writeAndFlush(logoutRequestPacket);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
