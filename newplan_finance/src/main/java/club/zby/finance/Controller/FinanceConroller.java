package club.zby.finance.Controller;


import club.zby.finance.Entity.Finance;
import club.zby.finance.Service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "Finance")
public class FinanceConroller {

    @Autowired
    private FinanceService financeService;

    @GetMapping(value = "showfinance")
    public List<Finance> finAllByWho(@PathVariable("who") String who){

        return financeService.finAllByWho(who);
    }

}
