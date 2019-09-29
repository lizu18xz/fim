package com.fayayo.fim.core;

import lombok.Getter;
import lombok.Setter;

/**
 * @author dalizu on 2019/1/10.
 * @version v1.0
 * @desc 登陆信息返回
 */
@Getter
@Setter
public class LoginInfo {

    public LoginInfo() {
    }

    public LoginInfo(URL url, String userId) {
        this.url = url;
        this.userId = userId;
    }

    private URL url;

    private String userId;

    @Override
    public String toString() {
        return "LoginInfo{" +
                "url=" + url.toAddress() +
                ", userId='" + userId + '\'' +
                '}';
    }
}
