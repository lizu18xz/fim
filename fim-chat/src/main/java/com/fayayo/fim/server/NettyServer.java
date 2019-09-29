package com.fayayo.fim.server;

import com.fayayo.fim.server.handler.LoginRequestHandler;
import com.fayayo.fim.server.handler.LogoutRequestHandler;
import com.fayayo.fim.core.URL;
import com.fayayo.fim.codec.PacketDecoder;
import com.fayayo.fim.codec.PacketEncoder;
import com.fayayo.fim.codec.Spliter;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @author dalizu on 2019/1/8.
 * @version v1.0
 * @desc 服务端
 */
@Slf4j
public class NettyServer {

    private NioEventLoopGroup boosGroup;
    private NioEventLoopGroup workerGroup;
    private ServerBootstrap serverBootstrap;

    private URL url;

    private CountDownLatch countDownLatch;

    public NettyServer(URL url,CountDownLatch countDownLatch) {
        this.url=url;
        this.countDownLatch=countDownLatch;
        init();
    }

    private void init(){

        boosGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {

                        ch.pipeline().addLast(new Spliter());

                        ch.pipeline().addLast(new PacketDecoder());

                        ch.pipeline().addLast(LoginRequestHandler.INSTANCE);

                        ch.pipeline().addLast(LogoutRequestHandler.INSTANCE);

                        ch.pipeline().addLast(new PacketEncoder());

                    }
                });

    }


    public void start(){

        Integer port=url.getPort();

        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                log.info(new Date() + ": 端口[" + port + "]绑定成功!");
                this.countDownLatch.countDown();
            } else {
                log.error("端口[" + port + "]绑定失败!");
            }
        });

    }


}
