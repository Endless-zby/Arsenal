package club.zby.newplan.controller;

import club.zby.newplan.result.Result;
import club.zby.newplan.result.StatusCode;
import club.zby.newplan.test.PayContextStrategy;
import club.zby.newplan.test.PayMode;
import club.zby.newplan.test.StrategyFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: 赵博雅
 * @Date: 2019/12/2 10:42
 */

@Api(value = "Enum")
@Controller
@RequestMapping(value = "Enum")
public class EnumText {

    @Autowired
    private PayContextStrategy payContextStrategy;


    @ResponseBody
    @ApiOperation(value="支付接口", notes="支付接口")
    @GetMapping("test")
    public Result EnumText(String Code){
        String result = null;
        try {
            result = payContextStrategy.toPayHtml(Code);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new Result(true, StatusCode.OK,"调用。。。",result);
    }

}
