package com.fayayo.fim.client.support;

import com.fayayo.fim.client.support.command.ConsoleCommandManager;
import com.fayayo.fim.client.support.command.LoginConsoleCommand;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import java.util.Scanner;

/**
 * @author dalizu on 2019/1/9.
 * @version v1.0
 * @desc
 */
@Slf4j
public class BasicClientConfigBean implements InitializingBean {

    private static Channel channel=null;

    @Override
    public void afterPropertiesSet() throws Exception {

        //进入客户端的命令行
        log.info("启动客户端");
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        Scanner scanner = new Scanner(System.in);

        new Thread(() -> {
            while (!Thread.interrupted()) {
                //首先要求登陆
                if(channel==null){
                    channel=loginConsoleCommand.exec(scanner);
                }else {
                    consoleCommandManager.exec(scanner,channel);
                }
            }
        }).start();

    }

}
