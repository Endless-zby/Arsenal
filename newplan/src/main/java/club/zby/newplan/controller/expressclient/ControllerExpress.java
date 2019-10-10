package club.zby.newplan.controller.expressclient;

import club.zby.newplan.result.Result;
import club.zby.newplan.result.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 赵博雅
 * @Date: 2019/9/25 11:33
 */

@Api(value = "express")
@Controller
@RequestMapping(value = "ExperssHandle")
public class ControllerExpress {

    @Autowired
    private ExpressClient expressClient;

    @ResponseBody
    @ApiOperation(value="快递", notes="即时查询")
    @GetMapping("getOrderTracesByJson")
    public Result getOrderTracesByJson(@RequestParam("shipChannel") String shipChannel, @RequestParam("shipSn") String shipSn, HttpServletRequest request){
        System.out.println(222);
        String status = (String) request.getAttribute("status");
        if("404".equals(status) || status == null){
            return new Result(false, StatusCode.LOGINERROR,"请先登录后操作",null);
        }
        return expressClient.getOrderTracesByJson(shipChannel, shipSn);
    }

    @ResponseBody
    @ApiOperation(value="快递", notes="单号识别")
    @GetMapping("getOrderShipperCode")
    public Result getOrderShipperCode(@RequestParam("shipSn") String shipSn, HttpServletRequest request){
        System.out.println(111);
        String status = (String) request.getAttribute("status");
        if("404".equals(status) || status == null){
            return new Result(false, StatusCode.LOGINERROR,"请先登录后操作",null);
        }
        return expressClient.getOrderShipperCode(shipSn);

    }





}
