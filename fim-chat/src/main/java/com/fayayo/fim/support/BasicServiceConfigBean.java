package com.fayayo.fim.support;

import com.fayayo.fim.server.NettyServer;
import com.fayayo.fim.api.Registry;
import com.fayayo.fim.core.URL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author dalizu on 2019/1/8.
 * @version v1.0
 * @desc 启动服务类
 */
@Slf4j
public class BasicServiceConfigBean implements InitializingBean{

    private Registry registry;

    private URL url;

    public BasicServiceConfigBean(URL url, Registry registry) {
        this.url=url;
        this.registry=registry;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("开始启动chat服务");

        startServer();

        register();
    }


    private void startServer(){

        //启动服务端
        CountDownLatch countDownLatch=new CountDownLatch(1);
        log.info("启动chat服务器:{}",url.toServerUrl());
        NettyServer server=new NettyServer(url,countDownLatch);
        server.start();
        try {
            countDownLatch.await(15, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void register(){
        //注册
        registry.register(url);
        log.info("chat服务器已经注册到服务中心");
    }

}
