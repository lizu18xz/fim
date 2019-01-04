package com.fayayo.fim.common.core;

/**
 * @author dalizu on 2019/1/2.
 * @version v1.0
 * @desc 服务地址信息
 */
public class URL {

    private String host;

    public int port;

    public URL() {
    }

    public URL(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
