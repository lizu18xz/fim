package com.fayayo.fim.client.support.command;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author dalizu on 2019/1/9.
 * @version v1.0
 * @desc
 */
public interface ConsoleCommand {


    void exec(Scanner scanner,Channel channel);

}
