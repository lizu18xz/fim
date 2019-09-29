package com.fayayo.register;

import com.fayayo.fim.api.Registry;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dalizu on 2019/1/2.
 * @version v1.0
 * @desc zk  autoConfig
 */
@Configuration
@EnableConfigurationProperties(URLRegistry.class)
@Slf4j
public class ZookeeperAutoConfiguration {

    @Autowired
    private URLRegistry url;

    @Bean(value = "registry")
    public Registry createRegistry() {
        try {
            String address = url.getAddress();
            int timeout = url.getTimeout();
            int sessionTimeout = url.getSessionTimeout();
            log.info("init ZookeeperRegistry,address[{}],sessionTimeout[{}],timeout[{}]", address, timeout, sessionTimeout);
            ZkClient zkClient = new ZkClient(address, sessionTimeout, timeout);
            return new ZookeeperRegistry(zkClient);
        } catch (ZkException e) {
            log.error("[ZookeeperRegistry] fail to connect zookeeper, cause: " + e.getMessage());
            throw e;
        }
    }


}
