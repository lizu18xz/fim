package com.fayayo.fim;

import com.fayayo.fim.core.URL;
import com.fayayo.register.ZookeeperRegistry;
import org.I0Itec.zkclient.ZkClient;

import java.util.List;

/**
 * @author dalizu on 2019/1/3.
 * @version v1.0
 * @desc
 */
public class ZkRegistryTest {


    public static void main(String[] args) {
        //测试zk功能
        String address="192.168.88.129:2181";
        int timeout = 1000;
        int sessionTimeout = 60 * 1000;
        ZkClient zkClient = new ZkClient(address, sessionTimeout, timeout);


        ZookeeperRegistry zookeeperRegistry= new ZookeeperRegistry(zkClient);

        //注册
        URL url=new URL();
        url.setHost("192.168.88.129");
        url.setPort(8085);
        zookeeperRegistry.register(url);

        //发现
        List<URL>dis= zookeeperRegistry.discover();

        for (URL u:dis){
            System.out.println(u.getHost()+":"+u.getPort());
        }

    }

}
