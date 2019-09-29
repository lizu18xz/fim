package com.fayayo.fim.client;

import com.fayayo.fim.client.handler.LoginResponseHandler;
import com.fayayo.fim.client.handler.LogoutResponseHandler;
import com.fayayo.fim.client.handler.MessageResponseHandler;
import com.fayayo.fim.codec.PacketDecoder;
import com.fayayo.fim.codec.PacketEncoder;
import com.fayayo.fim.codec.Spliter;
import com.fayayo.fim.core.URL;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author dalizu on 2019/1/9.
 * @version v1.0
 * @desc 客户端服务类
 */
@Slf4j
public class NettyClient {

    private NioEventLoopGroup workerGroup;

    private Bootstrap bootstrap;

    private ChannelFuture channelFuture = null;

    private Channel channel;

    private URL url;

    public NettyClient(URL url) {
        this.url=url;
        init();
    }

    private void init() {
        workerGroup = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {

                        ch.pipeline().addLast(new Spliter());

                        ch.pipeline().addLast(new PacketDecoder());

                        ch.pipeline().addLast(LoginResponseHandler.INSTANCE);

                        ch.pipeline().addLast(MessageResponseHandler.INSTANCE);

                        ch.pipeline().addLast(LogoutResponseHandler.INSTANCE);

                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });
    }

    public Channel connect() {

        String host=url.getHost();
        Integer port=url.getPort();
        channelFuture = bootstrap.connect(host, port);
        //连接超时时间
        int timeout = 3000;
        boolean result = channelFuture.awaitUninterruptibly(timeout, TimeUnit.MILLISECONDS);//阻塞直到连接建立
        boolean success = channelFuture.isSuccess();
        log.info("连接超时时间:{},连接是否成功:{}",timeout,(result && success));
        if (result && success) {
            channel = channelFuture.channel();//获取channel
        }
        return channel;
    }

}
