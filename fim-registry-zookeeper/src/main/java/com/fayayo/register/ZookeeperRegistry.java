package com.fayayo.register;

import com.fayayo.fim.api.Registry;
import com.fayayo.fim.api.WatchNotify;
import com.fayayo.fim.closable.ShutDownHook;
import com.fayayo.fim.core.URL;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.commons.lang3.StringUtils;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dalizu on 2019/1/2.
 * @version v1.0
 * @desc 服务注册发现的实现
 */
@Slf4j
public class ZookeeperRegistry implements Registry, Closeable {

    private ZkClient zkClient;

    private WatchNotify watchNotify;


    public ZookeeperRegistry(ZkClient zkClient) {
        this.zkClient = zkClient;

        log.info("zk register success!");

        String parentPath = URLParam.ZOOKEEPER_REGISTRY_NAMESPACE;
        try {
            if (!zkClient.exists(parentPath)) {
                log.info("init zookeeper registry namespace");
                zkClient.createPersistent(parentPath, true);
            }
            //监听
            zkClient.subscribeChildChanges(parentPath, new IZkChildListener() {
                //对父节点添加监听子节点变化。
                @Override
                public void handleChildChange(String parentPath, List<String> currentChilds) {
                    log.info(String.format("[ZookeeperRegistry] service list change: path=%s, currentChilds=%s", parentPath, currentChilds.toString()));
                    if(watchNotify!=null){
                        watchNotify.notify(nodeChildsToUrls(currentChilds));
                    }
                }
            });

            ShutDownHook.registerShutdownHook(this);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Failed to subscribe zookeeper");
        }
    }


    @Override
    public void register(URL url) {

        try {
            createNode(url, false);
        } catch (Throwable e) {
            e.printStackTrace();
            log.error("Failed to register url to zookeeper, cause: ", e.getMessage());
        } finally {

        }
    }

    @Override
    public List<URL> discover() {
        try {
            String parentPath = toParentNodePath();
            List<String> currentChilds = new ArrayList<String>();
            if (zkClient.exists(parentPath)) {
                currentChilds = zkClient.getChildren(parentPath);
            }
            return nodeChildsToUrls(currentChilds);
        } catch (Throwable e) {
            log.error("Failed to discover service  from zookeeper, cause:{}", e.getMessage());
        }

        return null;
    }

    @Override
    public void setWatchNotify(WatchNotify watchNotify) {
        this.watchNotify=watchNotify;
    }

    private List<URL> nodeChildsToUrls(List<String> currentChilds) {

        List<URL> urls = new ArrayList<URL>();
        if (currentChilds != null) {
            for (String node : currentChilds) {
                String nodePath = toParentNodePath() + URLParam.PATH_SEPARATOR + node;
                String data = null;
                try {
                    data = zkClient.readData(nodePath, true);
                } catch (Exception e) {
                    log.warn("get zkdata fail,nodePath:{},{}!", nodePath, e.getMessage());
                }
                URL newurl = null;
                if (StringUtils.isNotBlank(data)) {
                    try {
                        newurl = new URL();

                        String datas[] = data.split(":");

                        newurl.setHost(datas[0]);
                        newurl.setPort(Integer.parseInt(datas[1]));
                        newurl.setHttpPort(Integer.parseInt(datas[2]));

                    } catch (Exception e) {
                        log.warn(String.format("Found malformed urls from ZookeeperRegistry, path=%s", nodePath), e);
                    }
                }
                urls.add(newurl);
            }
        }
        return urls;
    }


    //创建zk节点
    private void createNode(URL url, boolean persistent) {

        String nodePath = toNodePath(url);

        if (persistent) {
            if (!zkClient.exists(nodePath)) {
                zkClient.createPersistent(nodePath, true);
            }
        } else {
            zkClient.createEphemeral(nodePath, url.toAddress());
        }
    }


    private String toParentNodePath() {

        return URLParam.ZOOKEEPER_REGISTRY_NAMESPACE;
    }

    private String toNodePath(URL url) {

        String nodePath = url.toRegisterPath();

        return toParentNodePath() + URLParam.PATH_SEPARATOR + nodePath;
    }

    @Override
    public void close() throws IOException {
        log.info("zk close");
        this.zkClient.close();
    }

}
