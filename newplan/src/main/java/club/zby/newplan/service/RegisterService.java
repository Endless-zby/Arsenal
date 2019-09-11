package club.zby.newplan.service;


import club.zby.newplan.Dao.UserDao;
import club.zby.newplan.EmailTemplate.HttpEmail;
import club.zby.newplan.config.IdWorker;
import club.zby.newplan.Entity.User;
import club.zby.newplan.result.Result;
import club.zby.newplan.result.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Transactional
    public Result register(User user, String smsCode){

        String id = idWorker.nextId() + "";

        String code = (String) redisTemplate.opsForValue().get("sms_" + user.getPhone());

        if(!smsCode.equals(code)){
            return new Result(false, StatusCode.REPEATERROR,"手机验证超时！未通过！",user.getPhone());
        }
        if (userDao.existsByPhone(user.getPhone())){
            return new Result(false,StatusCode.REPEATERROR,"该号码已经注册,请直接登录，或找回密码",user.getPhone());
        }
        user.setId(id);
        user.setPassword(encoder.encode(user.getPassword()));   //密码加密
        user.setStatus("0");
        user.setRegtime(new Date());
        user.setUpdate(new Date());
        userDao.save(user);
        redisTemplate.opsForValue().set(id,"success", 5, TimeUnit.MINUTES);
        //发送邮箱验证
        httpEmail.httpEmail(id,user.getEmail());
        return new Result(true,StatusCode.OK,"注册成功，完成邮箱验证即可登录！",user);

    }

}
