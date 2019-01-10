package com.fayayo.fim.connector.service;

import com.fayayo.fim.common.core.LoginInfo;
import com.fayayo.fim.connector.entity.User;

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
