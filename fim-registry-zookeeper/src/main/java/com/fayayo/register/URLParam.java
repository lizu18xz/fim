package com.fayayo.register;

/**
 * @author dalizu on 2019/1/2.
 * @version v1.0
 * @desc 如果没有配置则默认
 */
public class URLParam {

    private static final int SECOND_MILLS = 1000;

    private static final int MINUTE_MILLS = 60 * SECOND_MILLS;

    public static final String ADDRESS = "127.0.0.1:2181";

    public static final int CONNECTTIMEOUT = 1000;

    public static final int REGISTRYSESSIONTIMEOUT = 1 * MINUTE_MILLS;


    //zk注册 默认根路径
    public static final String ZOOKEEPER_REGISTRY_NAMESPACE = "/fim";

    public static final String PATH_SEPARATOR = "/";

}
