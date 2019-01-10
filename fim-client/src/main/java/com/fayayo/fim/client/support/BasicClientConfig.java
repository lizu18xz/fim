package com.fayayo.fim.client.support;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dalizu on 2019/1/9.
 * @version v1.0
 * @desc
 */
@Configuration
public class BasicClientConfig {


    @Bean
    public BasicClientConfigBean basicClientConfigBean(){


        return new BasicClientConfigBean();
    }


}
