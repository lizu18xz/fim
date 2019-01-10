package com.fayayo.fim.client.support.command;

import com.fayayo.fim.client.client.NettyClient;
import com.fayayo.fim.client.support.HttpReq;
import com.fayayo.fim.common.core.LoginInfo;
import com.fayayo.fim.common.core.URL;
import com.fayayo.fim.common.result.ResultVO;
import com.fayayo.fim.common.util.JsonMapper;
import com.fayayo.fim.transport.protocol.request.LoginRequestPacket;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sun.nio.ch.Net;

import java.util.Scanner;

/**
 * @author dalizu on 2019/1/9.
 * @version v1.0
 * @desc 登陆业务
 */
@Slf4j
public class LoginConsoleCommand  {


    public Channel exec(Scanner scanner) {

        log.info("请输入用户名和密码进行登录: ");
        String line=scanner.nextLine();
        String []userInfo=line.split(" ");
        if(userInfo.length!=2){
            log.info("请输入用户名和密码进行登录: [username] [pwd]");
            return null;
        }
        String username=userInfo[0];
        String password=userInfo[1];
        log.info("用户名:{},密码:{}",username,password);

        //首先登陆到connector获取chat服务器的地址
        LoginInfo loginInfo=getChatServer(username,password);
        if(loginInfo==null){
            return null;
        }
        //然后建立客户端到服务器的连接
        Channel channel=initClient(loginInfo.getUrl());

        //然后向服务端发送登陆请求
        sendLoginRequest(channel,username,loginInfo.getUserId());

        return channel;

    }

    public LoginInfo getChatServer(String username,String password) {

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("username",username);
        params.add("password",password);
        String result=restTemplate.postForObject(HttpReq.CONNECTOR_POST_LOGIN,params,String.class);
        log.info("request result:{}",result);
        ResultVO<LoginInfo> resultVO=JsonMapper.string2Obj(result,new TypeReference<ResultVO<LoginInfo>>() { });

        if(resultVO.getData()==null){
            log.info("login error");
            return null;
        }
        return resultVO.getData();
    }

    private Channel initClient(URL url) {
        NettyClient client=new NettyClient(url);
        return client.connect();
    }


    private void sendLoginRequest(Channel channel,String username,String userId) {

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUsername(username);
        loginRequestPacket.setUserId(userId);
        // 发送登录数据包
        channel.writeAndFlush(loginRequestPacket);

        waitForLoginResponse();
    }

    private  void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
