package com.fayayo.fim.controller;

import com.fayayo.fim.service.ChatService;
import com.fayayo.fim.core.SendToUserRequest;
import com.fayayo.fim.result.ResultVO;
import com.fayayo.fim.result.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dalizu on 2019/1/15.
 * @version v1.0
 * @desc
 */
@Slf4j
@RestController
@RequestMapping("/online/")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("sendToUser")
    public ResultVO sendToUser(@RequestBody SendToUserRequest sendToUserRequest){

        log.info("sendToUser params:{}",sendToUserRequest.toString());
        chatService.sendToUser(sendToUserRequest);

        return ResultVOUtil.success();
    }

    @PostMapping("broadcast")
    public ResultVO broadcast(@RequestBody SendToUserRequest sendToUserRequest){
        log.info("broadcast params:{}",sendToUserRequest.toString());

        chatService.broadcast(sendToUserRequest);

        return ResultVOUtil.success();
    }


    public void sendToGroup(){


    }

}
