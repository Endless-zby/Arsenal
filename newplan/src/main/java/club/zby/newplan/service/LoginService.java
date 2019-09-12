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
     * @param user
     * @return
     */
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


    /**
     * qq第三方登录逻辑
     * @param openid
     * @return
     */
    @Transactional
    public Result checkOpenId(String openid, Map<String, Object> qqProperties){
        //查询是否存在openid
        System.out.println(3);
        User user = userDao.findAllByQqopenid(openid);
        if(user == null){
            System.out.println(4);
            //获取数据
            User users = new User();
            try {
                QQUserInfo userInfo =  qqService.getUserInfo(openid,qqProperties);
                users.setId(idWorker.nextId() + "");
                users.setRegtime(new Date());
                users.setUpdatetime(new Date());
                users.setQqopenid(openid);
                users.setPhoto(userInfo.getFigureurl_qq());
                users.setNickname(userInfo.getNickname());
            } catch (Exception e) {
                e.printStackTrace();
            }
            //首次使用qq登录的用户，给其注册

            User save = userDao.save(users);
            if(save.getId() != null){
                System.out.println(5);
                //结束后跳转到手机验证界面，完成注册流程
                return new Result(false, StatusCode.PHONEERROR,"请进行手机验证",save);
            }

        }
        if(null != user.getQqopenid()){
            System.out.println(6);
            User users = userDao.findAllByQqopenid(openid);
            if("" != user.getPhone() || null != user.getPhone()){
                System.out.println(7);
                //手机号验证过了，直接登录吧
                return new Result(true, StatusCode.OK,"登录成功",users);
            }else {
                System.out.println(8);
                User save = userDao.findAllByQqopenid(openid);
                //手机还没验证过，先去验证手机号吧
                return new Result(false, StatusCode.PHONEERROR,"请进行手机验证",users);
            }
        }
        System.out.println(10);
        return new Result(false, StatusCode.ERROR,"注册失败",null);
    }

}
