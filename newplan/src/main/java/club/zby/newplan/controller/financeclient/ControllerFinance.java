package club.zby.newplan.controller.financeclient;

import club.zby.newplan.Entity.Finance;
import club.zby.newplan.result.Result;
import club.zby.newplan.result.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "finance")
@Controller
@RequestMapping(value = "FinanceHandle")
public class ControllerFinance {

    @Autowired
    private FinanceClient financeClient;

    @ResponseBody
    @ApiOperation(value="开销--查询", notes="查询")
    @GetMapping("showfinance")
    public Result finAllByWho(HttpServletRequest request){
        String status = (String) request.getAttribute("status");
        System.out.println(status);
        if("404".equals(status) || status == null){
            return new Result(false, StatusCode.LOGINERROR,"登录异常",null);
        }
        return financeClient.findAllByWho();
    }

    @ResponseBody
    @ApiOperation(value="开销--记录", notes="记录")
    @PostMapping(value = "savefinance")
    public Result saveFinance(@RequestBody Finance finance,HttpServletRequest request){
        String status = (String) request.getAttribute("status");
        if("404".equals(status) || status == null){
            return new Result(false,StatusCode.LOGINERROR,"登录异常",null);
        }
        finance.setWho((String) request.getAttribute("userid"));
        return financeClient.saveFinance(finance);
    }

    @ResponseBody
    @ApiOperation(value="开销--删除", notes="删除")
    @DeleteMapping(value = "delfinance/{id}")
    public Result delFinance(@PathVariable("id") String id,HttpServletRequest request){
        String status = (String) request.getAttribute("status");
        if("404".equals(status) || status == null){
            return new Result(false,StatusCode.LOGINERROR,"登录异常",null);
        }
        return financeClient.delFinance(id);
    }

    @ResponseBody
    @ApiOperation(value="开销--视图", notes="视图")
    @GetMapping(value = "financeview")
    public Result financeView(HttpServletRequest request){
        String status = (String) request.getAttribute("status");
        if("404".equals(status) || status == null){
            return new Result(false,StatusCode.LOGINERROR,"登录异常",null);
        }
        return financeClient.financeView();
    }

}
