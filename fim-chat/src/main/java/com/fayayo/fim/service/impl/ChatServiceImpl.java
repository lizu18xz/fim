package com.fayayo.fim.service.impl;

import com.fayayo.fim.service.ChatService;
import com.fayayo.fim.core.SendToUserRequest;
import com.fayayo.fim.protocol.response.MessageResponsePacket;
import com.fayayo.fim.util.SessionUtil;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;


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

    @Override
    public void broadcast(SendToUserRequest sendToUserRequest) {

        //构造发送请求包,转发客户端的请求
        String message=sendToUserRequest.getMsg();
        String fromUserId=sendToUserRequest.getUserId();
        //发送广播,轮训发送给所有的用户
        Map<String, Channel>  userIdChannelMap = SessionUtil.getChatChannel();

        log.info("当前chat服务器的连接数:{}",userIdChannelMap.size());

        //排除发送者

        for (Map.Entry<String, Channel> entry : userIdChannelMap.entrySet()) {
            if(entry.getKey().equals(fromUserId)){
                continue;
            }
            entry.getValue().writeAndFlush(new MessageResponsePacket(fromUserId,message));
        }
    }


}
