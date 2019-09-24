package club.zby.finance.Controller;


import club.zby.finance.Config.IdWorker;
import club.zby.finance.Entity.Finance;
import club.zby.finance.Service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import club.zby.finance.Config.Result;
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
            return new Result(false,10000,"查询失败",null);
        }
        return new Result(true,10002,"返回成功",finances);
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
            return new Result(true,10002,"添加成功",savefinance);
        }
        return new Result(false,10000,"失败，重试",null);
    }

}
