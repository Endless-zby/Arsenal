package club.zby.finance.Controller;


import club.zby.finance.Config.IdWorker;
import club.zby.finance.Entity.Finance;
import club.zby.finance.Service.FinanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import club.zby.finance.Config.Result;
import club.zby.finance.Config.StatusCode;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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

    /**
     * 展示所有的记录
     * @param who
     * @return
     */
//    @ApiOperation(value = "展示查询所有的记录", notes = "展示查询所有的记录", httpMethod = "GET")
    @ResponseBody
    @GetMapping("showfinance/{who}")
    public Result findAllByWho(@PathVariable("who") String who){
        List<Finance> finances = financeService.findAllByWho(who);
        if(finances.size() == 0){
            return new Result(false,StatusCode.RESERROR,"查询失败",null);
        }
        return new Result(true,StatusCode.OK,"返回成功",finances);
    }

    /**
     * 添加记录
     * @param finance
     * @return
     */
//    @ApiOperation(value = "添加记录", notes = "添加记录", httpMethod = "POST")
    @ResponseBody
    @PostMapping("savefinance")
    public Result saveFinance(@RequestBody Finance finance){
        finance.setId(idWorker.nextId() + "");
        finance.setTime(new Date());
        Finance savefinance = financeService.saveFinance(finance);
        if(savefinance != null){
            return new Result(true,StatusCode.OK,"添加成功",savefinance);
        }
        return new Result(false,StatusCode.RESERROR,"失败，重试",null);
    }

    /**
     * 删除记录   根据id
     * @param id
     * @return
     */
//    @ApiOperation(value = "删除记录", notes = "删除记录", httpMethod = "Delete")
    @ResponseBody
    @DeleteMapping("delfinance")
    public Result delFinance(@PathVariable("id") String id){
        int del =  financeService.delFinance(id);
        if(del > 0){
            return new Result(true,StatusCode.OK,"删除成功",del);
        }
        return new Result(false,StatusCode.RESERROR,"失败，请刷新",del);
    }

    /**
     * 记录的视图展示  数据处理
     * @param
     * @return
     */
    @ApiOperation(value = "记录的视图展示", notes = "数据处理", httpMethod = "GET")
//    @ApiImplicitParams({
//            @ApiImplicitParam(paramType="query",name = "userid",required = true, value = "用户标识", dataType = "String" )
//    })
    @ResponseBody
    @GetMapping("financeview")
    public Result financeView(){
        //返回提示被放置在financeService中！
        String authrorization = request.getHeader("Authrorization");
        String aaa = request.getHeader("aaa");
        String userid = (String) request.getAttribute("userid");

        System.out.println("转发的heard：" + authrorization);
        System.out.println("转发的heard：" + aaa);
        System.out.println("转发的userid：" + userid);
        return financeService.findAllByid("66341505371082752");
    }

}
