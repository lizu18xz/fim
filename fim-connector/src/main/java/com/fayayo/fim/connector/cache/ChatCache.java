package com.fayayo.fim.connector.cache;

import com.fayayo.fim.common.api.Registry;
import com.fayayo.fim.common.api.WatchNotify;
import com.fayayo.fim.common.core.URL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dalizu on 2019/1/8.
 * @version v1.0
 * @desc 对chat服务信息的缓存
 */
@Slf4j
@Service
public class ChatCache implements WatchNotify,InitializingBean{

    @Autowired
    private Registry registry;

    private static final String KEY="chat";

    private Map<String,List<URL>> cache=new HashMap<>();

    public List<URL> getAll(){
        List<URL>list=cache.get(KEY);
        if(CollectionUtils.isEmpty(list)){
            list=registry.discover();
            cache.put(KEY,list);
        }
        return list;
    }


    public void refresh(List<URL> urls){
        log.info("refresh urls");
        cache.put(KEY,urls);
    }

    @Override
    public void notify(List<URL> urls) {
        refresh(urls);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("init chat info");
        registry.setWatchNotify(this);
    }
}
