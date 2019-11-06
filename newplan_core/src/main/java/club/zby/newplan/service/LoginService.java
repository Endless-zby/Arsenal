package club.zby.newplan.service;

import club.zby.newplan.Dao.UserDao;
import club.zby.newplan.Entity.QQUserInfo;
import club.zby.newplan.Entity.User;
import club.zby.newplan.config.IdWorker;
import club.zby.newplan.result.Result;
import club.zby.newplan.result.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Map;

@Service
public class LoginService {

    @Autowired
    private BCryptPasswordEncoder encoder ;
    @Autowired
    private UserDao userDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private QQService qqService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 账号登录逻辑
     * @param username
     * @param password
     * @return
     */
    @Transactional
    public Result login(String username,String password){
        //数据库查询
        User users = userDao.findByUsername(username);
        //密码解密验证
        if(users != null && encoder.matches(password,users.getPassword())){
            System.out.println(00);
            return new Result(true, StatusCode.OK,"登录成功",users);
        }
        System.out.println(11);
        return new Result(false, StatusCode.LOGINERROR,"没有找到该用户",null);
    }


    /**
     * qq第三方登录逻辑
     * @param openid
     * @return
     */
    @Transactional
    public Result checkOpenId(String openid, Map<String, Object> qqProperties){
        //查询是否存在openid
        User user = userDao.findAllByQqopenid(openid);
        if(user == null){
            //不存在，直接注册
            //获取数据
            User users = new User();
            try {
                QQUserInfo userInfo =  qqService.getUserInfo(openid,qqProperties);
                users.setId(idWorker.nextId() + "");
                users.setRegtime(new Date());
                users.setStatus("1");
                users.setType("0");
                users.setUpdatetime(new Date());
                users.setQqopenid(openid);
                users.setGender(userInfo.getGender());
                users.setPhoto(userInfo.getFigureurl_qq());
                users.setNickname(userInfo.getNickname());
            } catch (Exception e) {
                e.printStackTrace();
            }
            //首次使用qq登录的用户，给其注册
            User save = userDao.save(users);
            if(save.getId() != null){
                //结束后跳转到手机验证界面，完成注册流程
                return new Result(true, StatusCode.OK,"注册成功",save);
            }

        }

        if(user != null && user.getId() != null){
            try {
                QQUserInfo userInfo =  qqService.getUserInfo(openid,qqProperties);
                System.out.println(userInfo);
                user.setPhoto(userInfo.getFigureurl_qq());
                user.setNickname(userInfo.getNickname());
                return new Result(true, StatusCode.OK,"登录成功",user);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return new Result(false, StatusCode.ERROR,"未知错误",null);
    }


    /**
     * 登陆后获取userinfo
     * @param userid
     * @return
     */
    @Transactional
    public User userInfo(String userid){
        return  userDao.findAllById(userid);
    }

}
