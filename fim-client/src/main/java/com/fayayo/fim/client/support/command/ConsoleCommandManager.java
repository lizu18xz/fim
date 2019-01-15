package com.fayayo.fim.client.support.command;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author dalizu on 2019/1/10.
 * @version v1.0
 * @desc
 */
@Slf4j
public class ConsoleCommandManager implements ConsoleCommand{


    private Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager() {
        consoleCommandMap = new HashMap<>();
        //指令合集(群聊,点对点聊...)

        consoleCommandMap.put("logout", new LogoutConsoleCommand());

        //指定用户
        consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());

        //广播所有
        consoleCommandMap.put("broadcast", new BroadcastConsoleCommand());

        //指定一个群

    }

    @Override
    public void exec(Scanner scanner,Channel channel) {
        //  获取第一个指令
        String command = scanner.next();

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);

        //执行命令
        if (consoleCommand != null) {
            consoleCommand.exec(scanner,channel);
        } else {
            log.error("无法识别[" + command + "]指令，请重新输入!");
        }

    }
}
