package club.zby.express.Controller;

import club.zby.express.Entity.Express;
import club.zby.express.ExpressApplication;
import club.zby.express.Untlis.ExpressUntlis;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @Author: 赵博雅
 * @Date: 2019/12/4 17:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExpressControllerTest extends ExpressController {

    @Autowired
    private ExpressUntlis expressUntlis;
    @Autowired
    private Express express;

    @Test
    public void testGetOrderTracesByJson() throws Exception {
        String requestData= "{'OrderCode':'','ShipperCode':'" + "4302599797574" + "','LogisticCode':'" + "YD" + "'}";
        System.out.println("请求报文：" + requestData);
        Map<String, String> params = new HashMap<String, String>();
        params.put("RequestData", expressUntlis.urlEncoder(requestData, "UTF-8")); //请求内容需进行URL(utf-8)编码。请求内容JSON格式，须和DataType一致。
        params.put("EBusinessID", express.getEBusinessID());   //商户ID，请在我的服务页面查看。
        params.put("RequestType", "1002");  //请求指令类型：即时查询API:1002
        String dataSign=expressUntlis.encrypt(requestData, express.getAppKey(), "UTF-8");    //数据内容签名：把(请求内容(未编码)+AppKey)进行MD5加密，然后Base64编码，最后 进行URL(utf-8)编码。
        params.put("DataSign", expressUntlis.urlEncoder(dataSign, "UTF-8"));
        params.put("DataType", "2");    //请求、返回数据类型：2-json；

        String result = expressUntlis.sendPost(express.getReqURL(), params);
        Object parse = JSON.parse(result);
        System.out.println("返回报文：" + parse);
    }
}