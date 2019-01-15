package com.fayayo.fim.client.client.handler;

import com.fayayo.fim.transport.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dalizu on 2019/1/15.
 * @version v1.0
 * @desc
 */
@Slf4j
@ChannelHandler.Sharable
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket>{

    public static final MessageResponseHandler INSTANCE=new MessageResponseHandler();

    private MessageResponseHandler(){

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) throws Exception {

        log.info("收到来自 {} 新的消息 {}",messageResponsePacket.getFromUserId(),messageResponsePacket.getMessage());

    }



}
