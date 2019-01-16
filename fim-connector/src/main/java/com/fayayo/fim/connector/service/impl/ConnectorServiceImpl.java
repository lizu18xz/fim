package com.fayayo.fim.connector.service.impl;

import com.fayayo.fim.common.core.SendToUserRequest;
import com.fayayo.fim.common.core.URL;
import com.fayayo.fim.common.exception.FimServiceException;
import com.fayayo.fim.common.result.ResultVO;
import com.fayayo.fim.common.util.JsonMapper;
import com.fayayo.fim.connector.cache.ChatCache;
import com.fayayo.fim.connector.constants.ChatReq;
import com.fayayo.fim.connector.constants.ConnectorErrorMsg;
import com.fayayo.fim.connector.key.UserChatKey;
import com.fayayo.fim.connector.service.ConnectorService;
import com.fayayo.fim.redis.RedisTemplate;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author dalizu on 2019/1/14.
 * @version v1.0
 * @desc
 */
@Slf4j
@Service
public class ConnectorServiceImpl implements ConnectorService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ChatCache chatCache;

    @Override
    public void userChat(SendToUserRequest sendToUserRequest) {

        //获取聊天的详细信息
        String toUserId=sendToUserRequest.getToUserId();

        URL url=redisTemplate.get(UserChatKey.userChatRel,toUserId,URL.class);

        if(url==null){
            log.info("用户:{}没有进行登陆",toUserId);
            throw new FimServiceException(ConnectorErrorMsg.USER_OFFLINE);
        }
        //发送请求到chat服务器
        log.info("向服务器发送请求:{}",url.toHttpUrl());

        sendToChat(sendToUserRequest,url,ChatReq.CHAT_POST_SEND_TO_USER);
    }

    @Override
    public void broadcast(SendToUserRequest sendToUserRequest) {

        //获取所有的chat服务器
        List<URL>urls= chatCache.getAll();
        for(URL url:urls){
            sendToChat(sendToUserRequest,url,ChatReq.CHAT_POST_BROADCAST);
        }
    }

    private void sendToChat(SendToUserRequest sendToUserRequest,URL url,String requesUrl) {

        //发起请求
        RestTemplate restTemplate = new RestTemplate();

        //获取chat地址
        String httpUrl=url.toHttpUrl();
        String result=restTemplate.postForObject(String.format(requesUrl, httpUrl),sendToUserRequest,String.class);

        log.info("request result:{}",result);
        ResultVO<String> resultVO= JsonMapper.string2Obj(result,new TypeReference<ResultVO<String>>() { });

        if(!resultVO.isSuccess()){
            log.error("request 【error】:{}",resultVO.getMsg());
        }

    }


}
