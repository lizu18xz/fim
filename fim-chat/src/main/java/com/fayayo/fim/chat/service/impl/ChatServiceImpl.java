package com.fayayo.fim.chat.service.impl;

import com.fayayo.fim.chat.service.ChatService;
import com.fayayo.fim.common.core.SendToUserRequest;
import com.fayayo.fim.transport.protocol.request.MessageRequestPacket;
import com.fayayo.fim.transport.protocol.response.MessageResponsePacket;
import com.fayayo.fim.transport.util.SessionUtil;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @author dalizu on 2019/1/15.
 * @version v1.0
 * @desc
 */
@Slf4j
@Service
public class ChatServiceImpl implements ChatService {


    @Override
    public void sendToUser(SendToUserRequest sendToUserRequest) {

        //获取接收者的channel

        try {
            String toUserId=sendToUserRequest.getToUserId();

            Channel channel=SessionUtil.getChannel(toUserId);

            //构造发送请求包,转发客户端的请求
            String message=sendToUserRequest.getMsg();

            String fromUserId=sendToUserRequest.getUserId();

            channel.writeAndFlush(new MessageResponsePacket(fromUserId,message));
        }catch (Exception e){
            log.error("send message error!!!");
            e.printStackTrace();
        }

    }




}
