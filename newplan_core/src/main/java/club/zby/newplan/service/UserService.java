package club.zby.newplan.service;

import club.zby.newplan.Dao.UserDao;
import club.zby.newplan.Entity.User;
import club.zby.newplan.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
     * 更新个人数据(不修改绑定手机)
     * @param user
     * @return
     */
    @Transactional
    public int UpDataInfo(User user){
        return userDao.UpDataInfo(user.getUsername(),user.getNickname(),user.getGender(),user.getEmail(),user.getUpdatetime(),user.getId());
    }

    /**
     * 更新个人数据(修改绑定手机)
     * @param user
     * @return
     */
    @Transactional
    public int updateUserInfoById(User user){
        return userDao.updateUserInfoById(user.getUsername(),user.getPhone(),user.getNickname(),user.getGender(),user.getEmail(),user.getUpdatetime(),user.getId());
    }

    /**
     * 校验要更改手机号是不是已被使用
     * @param phone
     * @return
     */
    @Transactional
    public boolean existsByPhone(String phone){
        return userDao.existsByPhone(phone);
    }

    /**
     * 校验该用户是否存在
     * @param userid
     * @return
     */
    @Transactional
    public boolean existsById(String userid){
        return userDao.existsById(userid);
    }

}
