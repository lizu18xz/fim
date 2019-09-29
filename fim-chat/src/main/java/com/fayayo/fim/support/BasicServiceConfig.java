package com.fayayo.fim.support;

import com.fayayo.fim.api.Registry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dalizu on 2019/1/8.
 * @version v1.0
 * @desc
 */
@Configuration
public class BasicServiceConfig {

    @Autowired
    private ChatRegistry chatRegistry;

    @Autowired
    private Registry registry;

    @Bean
    public BasicServiceConfigBean basicServiceConfigBean(){


        return new BasicServiceConfigBean(chatRegistry,registry);
    }


}
