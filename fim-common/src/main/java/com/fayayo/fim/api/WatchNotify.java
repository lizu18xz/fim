package com.fayayo.fim.api;

import com.fayayo.fim.core.URL;

import java.util.List;

/**
 * @author dalizu on 2019/1/9.
 * @version v1.0
 * @desc
 */
public interface WatchNotify {

    void notify(List<URL> urls);

}
