package com.fayayo.fim.chat.controller;

import com.fayayo.fim.chat.service.ChatService;
import com.fayayo.fim.common.core.SendToUserRequest;
import com.fayayo.fim.common.result.ResultVO;
import com.fayayo.fim.common.result.ResultVOUtil;
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

        log.info("params:{}",sendToUserRequest.toString());
        chatService.sendToUser(sendToUserRequest);

        return ResultVOUtil.success();
    }


    public void broadcast(){


    }


    public void sendToGroup(){


    }

}