package com.fayayo.fim.client.client.handler;

import com.fayayo.fim.transport.protocol.response.LoginResponsePacket;
import com.fayayo.fim.transport.session.Session;
import com.fayayo.fim.transport.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dalizu on 2019/1/10.
 * @version v1.0
 * @desc
 */
// 1. 加上注解标识，表明该 handler 是可以多个 channel 共享的
@ChannelHandler.Sharable
@Slf4j
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    public static final LoginResponseHandler INSTANCE = new LoginResponseHandler();

    private LoginResponseHandler(){

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        String userId = loginResponsePacket.getUserId();
        String userName = loginResponsePacket.getUsername();

        if (loginResponsePacket.isSuccess()) {

            log.info("[" + userName + "]登录成功，userId 为: " + userId);
            SessionUtil.bindSession(new Session(userId, userName), ctx.channel());
        } else {
            log.info("[" + userName + "]登录失败，原因：" + loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.info("~~客户端连接被关闭!(可能原因服务器退出了)~~");
        SessionUtil.unBindSession(ctx.channel());
    }

}
