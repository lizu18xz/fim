package com.fayayo.fim.support;

import com.fayayo.fim.core.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author dalizu on 2019/1/8.
 * @version v1.0
 * @desc
 */
@Configuration
@ConfigurationProperties(prefix = "fim.chat")
public class ChatRegistry extends URL {

}
