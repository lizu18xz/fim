package com.fayayo.fim.controller;

import com.fayayo.fim.core.LoginInfo;
import com.fayayo.fim.result.ResultEnum;
import com.fayayo.fim.result.ResultVO;
import com.fayayo.fim.result.ResultVOUtil;
import com.fayayo.fim.util.KeyUtil;
import com.fayayo.fim.entity.User;
import com.fayayo.fim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dalizu on 2019/1/2.
 * @version v1.0
 * @desc 提供注册和登陆服务
 */
@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResultVO register(User user){

        //用户注册
        user.setId(KeyUtil.genUniqueKey());
        userService.save(user);

        return ResultVOUtil.success();
    }


    @PostMapping("login")
    public ResultVO<LoginInfo> login(@RequestParam("username") String username, @RequestParam("password") String password){

        //用户登陆

        LoginInfo loginInfo=userService.userBind(username,password);
        if(loginInfo==null){
            return ResultVOUtil.error(ResultEnum.LOGIN_FAIL);
        }else {
            return ResultVOUtil.success(loginInfo);
        }
    }


    //用户下线
    public ResultVO logout(){

        return ResultVOUtil.success();
    }


    //连接不上服务器
    public void unBind(){

    }



}
