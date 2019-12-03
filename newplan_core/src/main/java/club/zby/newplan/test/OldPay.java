package club.zby.newplan.test;

import com.mysql.cj.util.StringUtils;
import io.netty.util.internal.StringUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @Author: 赵博雅
 * @Date: 2019/12/2 12:05
 */
@Component
public class OldPay {

    HashMap map = new HashMap<String,String>();


    public String PayMode(String Code){

        if(StringUtils.isNullOrEmpty(Code)){
            return "支付接口调用错误！";
        }
        if("ALiPay".equals(Code)){
            return "支付宝接口调用。。。。";
        }
        if("WeiChatPay".equals(Code)){
            return "微信支付接口调用。。。。";
        }
        if("Bank".equals(Code)){
            return "银联支付接口调用。。。。";
        }
       return "未接入。。";
    }

    public String PayModeType(String Code) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        map.put("ALiPay","club.zby.newplan.test.ALiPay");
        map.put("WeiChatPay","club.zby.newplan.test.WeiChatPay");
        map.put("BankPay","club.zby.newplan.test.BankPay");
        if(StringUtils.isNullOrEmpty(Code)){
            return "支付接口调用错误！";
        }
        Object object = Class.forName((String) map.get(Code)).newInstance();
        PayMode payMode = (PayMode)object;
        return payMode.PayModeType();
    }

}
