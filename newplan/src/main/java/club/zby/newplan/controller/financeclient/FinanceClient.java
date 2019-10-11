package club.zby.newplan.controller.financeclient;

import club.zby.newplan.Entity.Finance;
import club.zby.newplan.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Component
@FeignClient(name = "finance",fallback = FinanceClientImp.class)//uereka中的注册服务名，
public interface FinanceClient {

    @GetMapping(value = "Finance/showfinance/{who}")
    Result findAllByWho(@PathVariable("who") String who);

    @PostMapping(value = "Finance/savefinance")//原方法执行路径
    Result saveFinance(@RequestBody Finance finance);

    @DeleteMapping(value = "Finance/delfinance/{id}")
    Result delFinance(@PathVariable("id") String id);

    @GetMapping(value = "Finance/financeview")
    Result financeView(HttpServletRequest request);
}