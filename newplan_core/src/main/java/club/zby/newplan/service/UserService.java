package club.zby.newplan.service;

import club.zby.newplan.Dao.UserDao;
import club.zby.newplan.Entity.User;
import club.zby.newplan.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 赵博雅
 * @Date: 2019/11/22 16:30
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 查全部信息
     * @param userid
     * @return
     */
    public User UserInfo(String userid){
        return userDao.findAllById(userid);
    }

    /**
     * 更新个人数据
     * @param user
     * @return
     */
    public User UpDataInfo(User user){
        return userDao.save(user);
    }

}
