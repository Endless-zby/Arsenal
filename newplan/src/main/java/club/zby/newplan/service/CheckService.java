package club.zby.newplan.service;

import club.zby.newplan.Dao.UserDao;
import club.zby.newplan.entity.User;
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

    @Transactional
    public void smsService(String phone){
        String smsCode = ((int)(Math.random()*9000)+1000) + "";
        System.out.println("验证码：" + smsCode);
        redisTemplate.opsForValue().set("sms_" + phone,smsCode, time,TimeUnit.MINUTES);
        Map<String, String> map = new HashMap<>();
        map.put("phone",phone);
        map.put("smscode",smsCode);
        rabbitTemplate.convertAndSend("sms",map);
    }


    @Transactional
    public boolean onclock(String username){

        if(userDao.existsByUsername(username)){
            return false;
        }
        return true;
    }

}
