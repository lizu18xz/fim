package com.fayayo.fim.controller;

import com.fayayo.fim.core.SendToUserRequest;
import com.fayayo.fim.result.ResultVO;
import com.fayayo.fim.result.ResultVOUtil;
import com.fayayo.fim.service.ConnectorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dalizu on 2019/1/10.
 * @version v1.0
 * @desc
 */
@Slf4j
@RestController
@RequestMapping("/online/")
public class ConnectorController {

    @Autowired
    private ConnectorService connectorService;

    //私聊
    @PostMapping("userChat")
    public ResultVO userChat(@RequestBody SendToUserRequest sendToUserRequest){


        log.info("userChat params:{}",sendToUserRequest.toString());
        connectorService.userChat(sendToUserRequest);

        return ResultVOUtil.success();
    }

    //广播
    @PostMapping("broadcast")
    public ResultVO broadcast(@RequestBody SendToUserRequest sendToUserRequest){

        log.info("broadcast params:{}",sendToUserRequest.toString());
        connectorService.broadcast(sendToUserRequest);

        return ResultVOUtil.success();
    }


    //群聊
    @PostMapping("groupChat")
    public void groupChat(){


    }



}
