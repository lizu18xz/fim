package com.fayayo.fim.core;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author dalizu on 2019/1/2.
 * @version v1.0
 * @desc 服务地址信息
 */
public class URL {

    private String host;

    private int port;

    @Value("${server.port}")
    private int httpPort;

    public URL() {

    }

    public URL(String address) {
        String datas[]=address.split(":");
        if(datas.length!=3){
            throw new IllegalArgumentException("地址格式不对");
        }

        host=datas[0];
        port=Integer.parseInt(datas[1]);
        httpPort=Integer.parseInt(datas[2]);
    }

    public URL(String host, int port,int httpPort) {
        this.host = host;
        this.port = port;
        this.httpPort=httpPort;
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

    public String toAddress(){

        return host+":"+port+":"+httpPort;
    }

    public String toServerUrl(){

        return host+":"+port;
    }

    public String toHttpUrl(){

        return host+":"+httpPort;
    }

    public String toRegisterPath(){
        return host+"-"+port+"-"+httpPort;
    }

    public int getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(int httpPort) {
        this.httpPort = httpPort;
    }
}
