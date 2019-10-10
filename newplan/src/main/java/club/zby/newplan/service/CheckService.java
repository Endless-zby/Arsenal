package club.zby.newplan.service;

import club.zby.newplan.Dao.UserDao;
import club.zby.newplan.Entity.User;
import club.zby.newplan.result.Result;
import club.zby.newplan.result.StatusCode;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class CheckService {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Value("${sms.time}")
    private int time;
    @Autowired
    private UserDao userDao;

    @Transactional
    public boolean emailClack(String id){

        String status = (String)redisTemplate.opsForValue().get(id);

        if("success".equals(status)){
            userDao.updateStatus(id);
        }
        User user = userDao.findAllById(id);
        if ("0" == user.getStatus()){
            return false;
        }
        return true;
    }

    /**
     * 发送验证码
     * @param phone
     */
    @Transactional
    public Result smsService(String phone){
        if(userDao.existsByPhone(phone)){
            return new Result(false, StatusCode.PHONEERROR,"该号码已被注册，请更换或直接登录",phone);
        }
        String smsCode = ((int)(Math.random()*9000)+1000) + "";
        System.out.println("手机号：" + phone);
        System.out.println("验证码：" + smsCode);
        redisTemplate.opsForValue().set("sms_" + phone,smsCode, time,TimeUnit.MINUTES);
        Map<String, String> map = new HashMap<>();
        map.put("phone",phone);
        map.put("smscode",smsCode);
        rabbitTemplate.convertAndSend("sms",map);
        return new Result(true,StatusCode.OK,"发送成功",phone);
    }

    /**
     * 接受验证码 并记录手机号到当前的用户
     * @param phone
     * @param phonecheck
     */
    @Transactional
    public boolean smsCheckOut(String phone , String phonecheck,String userid){
        String phonecheck_redis  = (String)redisTemplate.opsForValue().get("sms_" + phone);
        if(phonecheck.equals(phonecheck_redis)){
            int num = userDao.updatePhoneById(phone, userid);
            System.out.println("更改数据：" + num);
            if (num != 0){
               return true;
            }
        }
        return false;
    }




    @Transactional
    public boolean onclock(String username){

        return userDao.existsByUsername(username);
    }

}
