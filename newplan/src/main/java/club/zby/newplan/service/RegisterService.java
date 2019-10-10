package club.zby.newplan.service;


import club.zby.newplan.Dao.UserDao;
import club.zby.newplan.EmailTemplate.HttpEmail;
import club.zby.newplan.config.IdWorker;
import club.zby.newplan.Entity.User;
import club.zby.newplan.result.Result;
import club.zby.newplan.result.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class RegisterService {


    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder encoder ;
    @Autowired
    private HttpEmail httpEmail;
    @Value("${UserInfo.Photo}")
    private String Photo;
    @Value("${UserInfo.Nickname}")
    private String Nickname;
    @Value("${UserInfo.Gender}")
    private String Gender;

    @Transactional
    public Result register(User user, String smsCode){

        String code = (String) redisTemplate.opsForValue().get("sms_" + user.getPhone());

        if(!smsCode.equals(code)){
            return new Result(false, StatusCode.PHONEERROR,"验证码错误！",user.getPhone());
        }
        String id = idWorker.nextId() + "";
        user.setId(id);
        user.setPassword(encoder.encode(user.getPassword()));   //密码加密
        user.setUsername(user.getPhone());
        if("1".equals(user.getType())){
            user.setStatus("0");
        }else {
            user.setStatus("1");
        }
        user.setRegtime(new Date());
        user.setUpdate(new Date());
        user.setNickname(Nickname + ((int)(Math.random() * 10000) + 10000));
        user.setPhoto(Photo);
        user.setGender(Gender);
        userDao.save(user);
//        redisTemplate.opsForValue().set(id,"success", 5, TimeUnit.MINUTES);
        //发送邮箱验证
//        httpEmail.httpEmail(id,user.getEmail());
        return new Result(true,StatusCode.OK,"注册成功",user);

    }

}
