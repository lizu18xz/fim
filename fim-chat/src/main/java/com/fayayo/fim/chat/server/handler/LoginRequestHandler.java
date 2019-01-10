package com.fayayo.fim.chat.server.handler;

import com.fayayo.fim.transport.protocol.request.LoginRequestPacket;
import com.fayayo.fim.transport.protocol.response.LoginResponsePacket;
import com.fayayo.fim.transport.session.Session;
import com.fayayo.fim.transport.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * @author dalizu on 2019/1/10.
 * @version v1.0
 * @desc
 */
// 1. 加上注解标识，表明该 handler 是可以多个 channel 共享的
    @Slf4j
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket>{

    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

    private LoginRequestHandler(){

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {

        String userId=loginRequestPacket.getUserId();
        String username=loginRequestPacket.getUsername();
        log.info(new Date() + ": 收到客户端登录请求……"+loginRequestPacket.toString());
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        loginResponsePacket.setUsername(username);
        loginResponsePacket.setUserId(userId);
        loginResponsePacket.setSuccess(true);
        log.info("[" + loginRequestPacket.getUsername() + "]登录成功");

        //服务端绑定登陆成功标志
        SessionUtil.bindSession(new Session(userId, username), ctx.channel());
        // 登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("~~有客户端连接了~~");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.info("~~有客户端退出了~~");
        SessionUtil.unBindSession(ctx.channel());
    }

}
