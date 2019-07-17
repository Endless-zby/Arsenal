package com.zby.service;


import com.zby.dao.UserDao;
import com.zby.entity.User;
import javafx.scene.control.TabPane;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userdao;
    @Autowired
    private BCryptPasswordEncoder encoder ;

    /**
     * 注册---结果返回取id
     * @param user
     * @return
     */
    public String register(User user){

        String password = encoder.encode(user.getPassword());

        user.setPassword(password);

        User save = userdao.save(user);

        return save.getId();
    }

    /**
     * 登录---返回用户对象
     * @param user
     * @return
     */
    public User login(User user){

        User userdata = userdao.findByUsername(user.getUsername());

        if(userdata != null && encoder.matches(user.getPassword(), userdata.getPassword())){
            return userdata;
        }
            return null;
    }
}
