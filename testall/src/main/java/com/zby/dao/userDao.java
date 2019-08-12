package com.zby.dao;

import com.zby.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: 赵博雅
 * @Date: 2019/8/5 10:02
 */
public interface userDao extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {

    User findAllByPhone(String phone);

}
