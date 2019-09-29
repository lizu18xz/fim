package com.fayayo.fim.service;

import com.fayayo.fim.core.LoginInfo;
import com.fayayo.fim.entity.User;

/**
 * @author dalizu on 2019/1/2.
 * @version v1.0
 * @desc
 */
public interface UserService {


    User findUserByUserNameAndPassword(String username,String password);

    void save(User user);

    LoginInfo userBind(String username, String password);

}
