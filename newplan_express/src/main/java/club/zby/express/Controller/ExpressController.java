package club.zby.express.Controller;

import club.zby.commen.Config.Result;
import club.zby.commen.Config.StatusCode;
import club.zby.express.Entity.Express;
import club.zby.express.Untlis.ExpressUntlis;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "Express")
@Api(value = "物流追踪接口")
public class ExpressController {


    @Autowired
    private ExpressUntlis expressUntlis;
    @Autowired
    private Express express;


    @ApiOperation(value = "根据物流单号和快递公司编码查询物流信息", notes = "根据物流单号和快递公司编码查询物流信息", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "shipChannel",required = true, value = "快递公司编码",dataType = "String"),
            @ApiImplicitParam(paramType="query",name = "shipSn",required = true, value = "物流单号", dataType = "String" )
    })
    @ResponseBody
    @GetMapping("getOrderTracesByJson")
    public Result getOrderTracesByJson(@RequestParam("shipChannel") String shipChannel, @RequestParam("shipSn") String shipSn) throws Exception{
        String requestData= "{'OrderCode':'','ShipperCode':'" + shipChannel + "','LogisticCode':'" + shipSn + "'}";
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

        return new Result(true, StatusCode.OK,"查询结果集",parse);
    }


    @ApiOperation(value = "根据物流单号查询快递公司编码", notes = "根据物流单号查询快递公司编码", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name = "shipSn",required = true, value = "物流单号", dataType = "String" )
    })
    @ResponseBody
    @GetMapping("/getOrderShipperCode")
    public Result getOrderShipperCode(@RequestParam("shipSn") String shipSn) throws Exception{
        String requestData= "{'LogisticCode':'" + shipSn + "'}";
        System.out.println("请求报文：" + requestData);
        Map<String, String> params = new HashMap<String, String>();
        params.put("RequestData", expressUntlis.urlEncoder(requestData, "UTF-8"));
        params.put("EBusinessID", express.getEBusinessID());
        params.put("RequestType", "2002");
        String dataSign=expressUntlis.encrypt(requestData, express.getAppKey(), "UTF-8");
        params.put("DataSign", expressUntlis.urlEncoder(dataSign, "UTF-8"));
        params.put("DataType", "2");

        String result=expressUntlis.sendPost(express.getReqURL(), params);
        Object parse = JSON.parse(result);
        System.out.println("返回报文：" + parse);

        return new Result(true, StatusCode.OK,"查询结果集",parse);
    }

}
