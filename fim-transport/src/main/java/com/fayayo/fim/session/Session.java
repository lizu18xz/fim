package com.fayayo.fim.session;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dalizu on 2019/1/10.
 * @version v1.0
 * @desc
 */
@Data
@NoArgsConstructor
public class Session {

    // 用户唯一性标识
    private String userId;
    private String userName;

    public Session(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return userId + ":" + userName;
    }

}
