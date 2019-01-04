package com.fayayo.fim.zookeeper;

import com.fayayo.fim.common.core.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author dalizu on 2019/1/2.
 * @version v1.0
 * @desc 配置约定
 */
@ConfigurationProperties(prefix = "fim.register")
@Configuration
public class URLRegistry {

    private String address;

    private int timeout;

    private int sessionTimeout;

    public String getAddress() {
        if (address == null) {
            address = URLParam.ADDRESS;
        }
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getTimeout() {
        if (timeout == 0) {
            timeout = URLParam.CONNECTTIMEOUT;
        }
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getSessionTimeout() {
        if (sessionTimeout == 0) {
            sessionTimeout = URLParam.REGISTRYSESSIONTIMEOUT;
        }
        return sessionTimeout;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

}
