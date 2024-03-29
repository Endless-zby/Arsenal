package club.zby.finance.Controller;


import club.zby.commen.Config.IdWorker;
import club.zby.finance.Entity.Finance;
import club.zby.finance.Service.FinanceService;
import club.zby.finance.Untlis.ToToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import club.zby.commen.Config.Result;
import club.zby.commen.Config.StatusCode;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "Finance")
@Api(value = "开销模块接口测试平台")
public class FinanceConroller {

    @Autowired
    private FinanceService financeService;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ToToken toToken;

    /**
     * 展示所有的记录
     *
     * @return
     */
    @ApiOperation(value = "展示查询所有的记录", notes = "展示查询所有的记录")
    @ResponseBody
    @GetMapping("showfinance")
    public Result findAllByWho() {

        String token = request.getHeader("Authrorization");
        Result result = toToken.parseToken(token);
        String userid = (String) result.getData();
        if (userid != null) {
            List<Finance> finances = financeService.findAllByWho(userid);
            return new Result(true, StatusCode.OK, "返回成功", finances);
        }
        return result;
    }

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @ResponseBody
    @GetMapping("selfinance/{page}")
    public Result findAllByPage(@PathVariable("page") int page) {

        try {
            String token = request.getHeader("Authrorization");
            Result result = toToken.parseToken(token);
            String userid = (String) result.getData();
            if (userid != null) {
                Page<Finance> finances = financeService.findAllByPage(userid, page);
                BigDecimal bigDecimal = financeService.sumMoney();
                return new Result(true, StatusCode.OK, bigDecimal.toString(), finances);
            }
        } catch (Exception e) {
            return new Result(false, StatusCode.ERROR, e.getMessage(), null);
        }

        return new Result(false, StatusCode.ERROR, "请重试", null);
    }


    /**
     * 添加记录
     *
     * @param finance
     * @return
     */
    @ApiOperation(value = "添加记录", notes = "添加记录")
    @ResponseBody
    @PostMapping("savefinance")
    public Result saveFinance(@RequestBody Finance finance) {

        if (finance.getWho() != null) {
            finance.setId(idWorker.nextId() + "");
            finance.setTime(new Date());
            finance.setStatus(0);
            finance.setTag1("1");
            Finance savefinance = financeService.saveFinance(finance);
            if (savefinance != null) {
                return new Result(true, StatusCode.OK, "添加成功", savefinance);
            }
            return new Result(false, StatusCode.RESERROR, "失败,请重试", null);
        }
        return new Result(false, StatusCode.RESERROR, "失败,请重试", null);
    }

    /**
     * 删除记录   根据id
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除记录", notes = "删除记录")
    @ResponseBody
    @GetMapping("delfinance/{id}")
    public Result delFinance(@PathVariable("id") String id) {

        int del = financeService.delFinance(id);
        if (del > 0) {
            return new Result(true, StatusCode.OK, "删除成功", del);
        }
        return new Result(false, StatusCode.RESERROR, "失败,请刷新重试", del);


    }

    /**
     * 记录的视图展示  数据处理
     *
     * @param
     * @return
     */
    @ApiOperation(value = "记录的视图展示", notes = "数据处理")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType="query",name = "userid",required = true, value = "用户标识", dataType = "String" )
//    })
    @ResponseBody
    @GetMapping("financeview")
    public Result financeView() {
        //返回提示被放置在financeService中！
        String token = request.getHeader("Authrorization");
        Result result = toToken.parseToken(token);
        String userid = (String) result.getData();
        if (userid != null) {
            return financeService.findAllByid(userid);
        }
        return result;
    }

}
