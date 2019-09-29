package com.fayayo.fim.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author dalizu on 2019/1/2.
 * @version v1.0
 * @desc 用户实体类
 */
@Entity(name = "t_user")
@Getter
@Setter
public class User {

    @Id
    private String id;

    private String username;

    private String password;

    private Integer sex;

    private Integer age;

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
