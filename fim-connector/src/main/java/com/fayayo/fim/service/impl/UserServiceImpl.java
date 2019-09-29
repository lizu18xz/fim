package com.fayayo.fim.service.impl;

import com.fayayo.fim.cache.ChatCache;
import com.fayayo.fim.core.LoginInfo;
import com.fayayo.fim.core.URL;
import com.fayayo.fim.exception.FimServiceException;
import com.fayayo.fim.constants.ConnectorErrorMsg;
import com.fayayo.fim.entity.User;
import com.fayayo.fim.loadbalance.RoundRobinLoadBalance;
import com.fayayo.fim.repository.UserRepository;
import com.fayayo.fim.service.UserChatService;
import com.fayayo.fim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dalizu on 2019/1/7.
 * @version v1.0
 * @desc
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserChatService userChatService;

    @Autowired
    private ChatCache chatCache;

    @Override
    public User findUserByUserNameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username,password);
    }

    @Override
    public void save(User user) {

        userRepository.save(user);
    }

    @Override
    public LoginInfo userBind(String username, String password) {

        User user=findUserByUserNameAndPassword(username,password);
        if(user!=null){

            //判断是否已经登陆
            URL url=userChatService.getChatByUser(user.getId());
            if(url!=null){
                return new LoginInfo(url,user.getId());
            }

            //从注册中心获取一个可用的chat服务
            RoundRobinLoadBalance<URL> robinLoadBalance=new RoundRobinLoadBalance<>();

            url=robinLoadBalance.doSelect(chatCache.getAll());
            if(url==null){
                throw new FimServiceException(ConnectorErrorMsg.CHAT_SERVICE_NOFOUND);
            }

            //保存用户和chat服务的对应关系,user_key_id:address
            boolean bind=userChatService.bindUserChat(user.getId(),url);
            if(!bind){
                throw new FimServiceException(ConnectorErrorMsg.REDIS_ERROR);
            }
            //返回可用的chat服务器
            return new LoginInfo(url,user.getId());
        }
        return null;
    }
}
