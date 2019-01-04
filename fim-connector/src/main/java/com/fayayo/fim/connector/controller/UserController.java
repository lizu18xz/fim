package com.fayayo.fim.connector.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dalizu on 2019/1/2.
 * @version v1.0
 * @desc 提供注册和登陆服务
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @PostMapping("register")
    public void register(){
        //用户注册


    }


    @PostMapping("login")
    public void login(){

        //用户登陆

        //获取一个可用的chat服务

        //保存用户和chat服务的对应关系

        //返回可用的chat服务器

    }



}
