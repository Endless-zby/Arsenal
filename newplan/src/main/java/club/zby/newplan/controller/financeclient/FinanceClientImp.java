package club.zby.newplan.controller.financeclient;

import club.zby.newplan.Entity.Finance;
import club.zby.newplan.result.Result;
import club.zby.newplan.result.StatusCode;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * finance服务的熔断机制
 */
@Component
public class FinanceClientImp implements FinanceClient {
    @Override
    public Result findAllByWho(String who) {
        return new Result(false, StatusCode.RESERROR, "网络开小差了，请重试", null);
    }

    @Override
    public Result saveFinance(Finance finance) {
        return new Result(false,  StatusCode.RESERROR, "网络开小差了，请重试", null);
    }

    @Override
    public Result delFinance(String id) {
        return new Result(false,  StatusCode.RESERROR, "网络开小差了，请重试", null);
    }
}
