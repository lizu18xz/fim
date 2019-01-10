package com.fayayo.fim.client.client.handler;

import com.fayayo.fim.transport.protocol.response.LogoutResponsePacket;
import com.fayayo.fim.transport.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author dalizu on 2019/1/10.
 * @version v1.0
 * @desc
 */
@ChannelHandler.Sharable
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket>{

    public static final LogoutResponseHandler INSTANCE = new LogoutResponseHandler();

    private LogoutResponseHandler(){

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket logoutResponsePacket) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }


}
