package com.fayayo.fim.connector.repository;
import com.fayayo.fim.connector.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dalizu on 2019/1/7.
 * @version v1.0
 * @desc
 */
public interface UserRepository extends JpaRepository<User,String>{


    User findByUsernameAndPassword(String username,String password);


}
