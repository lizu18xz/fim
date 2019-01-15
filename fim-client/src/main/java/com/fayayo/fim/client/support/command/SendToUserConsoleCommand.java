package com.fayayo.fim.client.support.command;

import com.fayayo.fim.client.support.HttpReq;
import com.fayayo.fim.common.core.SendToUserRequest;
import com.fayayo.fim.common.result.ResultVO;
import com.fayayo.fim.common.util.JsonMapper;
import com.fayayo.fim.transport.session.Session;
import com.fayayo.fim.transport.util.SessionUtil;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

/**
 * @author dalizu on 2019/1/14.
 * @version v1.0
 * @desc 发送给指定用户
 */
@Slf4j
public class SendToUserConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner,Channel channel) {
        log.info("请输入对方的用户ID和要发送的信息:");
        String toUserId = scanner.next();
        String message = scanner.next();
        //组装  [fromUserId]   [toUserId]  [message]
        //获取当前的user
        Session session=SessionUtil.getSession(channel);
        SendToUserRequest sendToUserRequest=new SendToUserRequest();
        sendToUserRequest.setUserId(session.getUserId());
        sendToUserRequest.setMsg(message);
        sendToUserRequest.setToUserId(toUserId);

        //发起请求
        RestTemplate restTemplate = new RestTemplate();

        String result=restTemplate.postForObject(HttpReq.CONNECTOR_POST_ONLINE_USER_CHAT,sendToUserRequest,String.class);

        log.info("request result:{}",result);
        ResultVO<String> resultVO= JsonMapper.string2Obj(result,new TypeReference<ResultVO<String>>() { });

        if(!resultVO.isSuccess()){
            log.error("request 【error】:{}",resultVO.getMsg());
        }


    }

}
