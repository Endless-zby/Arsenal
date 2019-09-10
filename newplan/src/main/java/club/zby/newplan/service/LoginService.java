package club.zby.newplan.service;

import club.zby.newplan.Dao.UserDao;
import club.zby.newplan.entity.User;
import club.zby.newplan.result.Result;
import club.zby.newplan.result.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LoginService {

    @Autowired
    private BCryptPasswordEncoder encoder ;
    @Autowired
    private UserDao userDao;

    @Transactional
    public Result login(User user){
        //数据库查询
        User users = userDao.findByUsername(user.getUsername());
        //密码解密验证
        if(users != null && encoder.matches(user.getPassword(),users.getPassword())){
            if("0".equals(users.getStatus())){
                return new Result(false, StatusCode.LOGINERROR,"账号未激活，使用邮件激活账号",users.getId());
            }
            return new Result(true, StatusCode.OK,"登录成功",users);
        }
        return new Result(false, StatusCode.LOGINERROR,"没有找到该用户",null);
    }

}
