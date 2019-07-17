package com.zby.dao;

import com.zby.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {

    User findByUsernameAndPassword(String username,String password);

    User findByUsername(String username);

}
