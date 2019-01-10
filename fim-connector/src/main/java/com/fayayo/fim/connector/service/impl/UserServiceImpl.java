package com.fayayo.fim.connector.service.impl;

import com.fayayo.fim.common.core.LoginInfo;
import com.fayayo.fim.common.core.URL;
import com.fayayo.fim.common.exception.FimServiceException;
import com.fayayo.fim.connector.cache.ChatCache;
import com.fayayo.fim.connector.constants.ConnectorErrorMsg;
import com.fayayo.fim.connector.entity.User;
import com.fayayo.fim.connector.key.UserChatKey;
import com.fayayo.fim.connector.loadbalance.RoundRobinLoadBalance;
import com.fayayo.fim.connector.repository.UserRepository;
import com.fayayo.fim.connector.service.UserService;
import com.fayayo.fim.redis.RedisTemplate;
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
    private RedisTemplate redisTemplate;

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

            //从注册中心获取一个可用的chat服务
            RoundRobinLoadBalance<URL>robinLoadBalance=new RoundRobinLoadBalance<>();

            URL url=robinLoadBalance.doSelect(chatCache.getAll());
            if(url==null){
                throw new FimServiceException(ConnectorErrorMsg.CHAT_SERVICE_NOFOUND);
            }

            //判断是否已经登陆
            url=redisTemplate.get(UserChatKey.userChatRel,user.getId(),URL.class);
            if(url!=null){
                return new LoginInfo(url,user.getId());
            }
            //保存用户和chat服务的对应关系,user_key_id:address
            boolean bind=redisTemplate.set(UserChatKey.userChatRel,user.getId(),url);
            if(!bind){
                throw new FimServiceException(ConnectorErrorMsg.REDIS_ERROR);
            }
            //返回可用的chat服务器
            return new LoginInfo(url,user.getId());
        }
        return null;
    }
}
