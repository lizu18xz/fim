package com.fayayo.fim.server.handler;

import com.fayayo.fim.protocol.request.LogoutRequestPacket;
import com.fayayo.fim.protocol.response.LogoutResponsePacket;
import com.fayayo.fim.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dalizu on 2019/1/10.
 * @version v1.0
 * @desc
 */
@Slf4j
@ChannelHandler.Sharable
public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    private LogoutRequestHandler(){

    }

    public static final LogoutRequestHandler INSTANCE=new LogoutRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket logoutRequestPacket) throws Exception {

        SessionUtil.unBindSession(ctx.channel());
        LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
        logoutResponsePacket.setSuccess(true);
        ctx.channel().writeAndFlush(logoutResponsePacket);
    }
}
