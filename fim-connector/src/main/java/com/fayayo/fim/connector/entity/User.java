package com.fayayo.fim.connector.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author dalizu on 2019/1/2.
 * @version v1.0
 * @desc 用户实体类
 */
@Getter
@Setter
public class User {

    private String id;

    private String username;

    private String password;

    private String sex;

    private String age;

    private Integer role;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
