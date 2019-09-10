package sms.listener;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import sms.util.SmsUtil;

import java.util.Map;

@Component
@RabbitListener(queues = "sms")
public class SmsListener {
    @Autowired
    private SmsUtil smsUtil ;
    @Value("${aliyun.sms.templateCode}")
    private String templateCode ;

    @Value("${aliyun.sms.signName}")
    private String signName  ;




    @RabbitHandler
    public void sendSms(Map<String,String> map) throws ClientException {
        //先从MQ取
        String phone = map.get("phone") ;
        String smscode = map.get("smscode") ;
        String smscodeJsonStr = "{\"code\":\""+smscode+"\"}" ;

        System.out.println("手机号：" +phone) ;
        System.out.println("验证码：" +smscode) ;
        //发送
            smsUtil.sendSms(phone, templateCode, signName, smscodeJsonStr);
    }
}
