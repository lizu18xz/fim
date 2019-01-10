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

    public URL(String address) {
        String datas[]=address.split(":");
        if(datas.length!=2){
            throw new IllegalArgumentException("地址格式不对");
        }

        host=datas[0];
        port=Integer.parseInt(datas[1]);
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

    public String toAddress(){

        return host+":"+port;
    }

    public String toRegisterPath(){
        return host+"-"+port;
    }

}
