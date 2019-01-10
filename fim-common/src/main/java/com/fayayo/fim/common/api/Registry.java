package com.fayayo.fim.common.api;


import com.fayayo.fim.common.core.URL;

import java.util.List;

/**
 * @author dalizu on 2019/1/2.
 * @version v1.0
 * @desc 服务注册接口
 */
public interface Registry {

    /**
     * register service to registry
     *
     * @param
     */
    void register(URL url);


    List<URL> discover();


    void setWatchNotify(WatchNotify watchNotify);

}
