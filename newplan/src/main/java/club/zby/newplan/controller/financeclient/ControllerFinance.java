package club.zby.newplan.controller.financeclient;

import club.zby.newplan.Entity.Finance;
import club.zby.newplan.result.Result;
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
    @ApiOperation(value="开销", notes="查询")
    @GetMapping("showfinance/{who}")
    public Result finAllByWho(@PathVariable("who") String who,HttpServletRequest request){
        String status = (String) request.getAttribute("status");
        if("404".equals(status) || status == null){
            return new Result(false,10000,"滚去登录",null);
        }
        return financeClient.findAllByWho(who);
    }

    @ResponseBody
    @ApiOperation(value="开销", notes="记录")
    @PostMapping(value = "savefinance")
    public Result saveFinance(@RequestBody Finance finance,HttpServletRequest request){
        String status = (String) request.getAttribute("status");
        if("404".equals(status) || status == null){
            return new Result(false,10000,"滚去登录",null);
        }
        finance.setWho((String) request.getAttribute("userid"));
        return financeClient.saveFinance(finance);
    }
}
