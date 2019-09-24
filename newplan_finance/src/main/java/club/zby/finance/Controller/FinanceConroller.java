package club.zby.finance.Controller;


import club.zby.finance.Config.IdWorker;
import club.zby.finance.Entity.Finance;
import club.zby.finance.Service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import club.zby.finance.Config.Result;
import club.zby.finance.Config.StatusCode;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "Finance")
public class FinanceConroller {

    @Autowired
    private FinanceService financeService;
    @Autowired
    private IdWorker idWorker;

    /**
     * 展示所有的记录
     * @param who
     * @return
     */
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
    @ResponseBody
    @DeleteMapping("delfinance/{id}")
    public Result delFinance(@PathVariable("id") String id){
        int del =  financeService.delFinance(id);
        if(del > 0){
            return new Result(true,StatusCode.OK,"删除成功",del);
        }
        return new Result(false,StatusCode.RESERROR,"失败，请刷新",del);
    }

}
