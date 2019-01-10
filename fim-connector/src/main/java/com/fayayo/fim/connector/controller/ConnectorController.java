package com.fayayo.fim.connector.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dalizu on 2019/1/10.
 * @version v1.0
 * @desc
 */
@RestController
@RequestMapping("/con/")
public class ConnectorController {


    //私聊
    @PostMapping("userChat")
    public void userChat(){


    }


    //群聊
    @PostMapping("groupChat")
    public void groupChat(){


    }



}
