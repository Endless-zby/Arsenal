package club.zby.newplan.controller.expressclient;

import club.zby.newplan.result.Result;
import club.zby.newplan.result.StatusCode;
import org.springframework.stereotype.Component;

/**
 * @Author: 赵博雅
 * @Date: 2019/9/25 11:33
 */
@Component
public class ExpressClientImp implements ExpressClient {
    @Override
    public Result getOrderTracesByJson(String shipChannel, String shipSn) {
        return new Result(false, StatusCode.RESERROR, "网络开小差了，请重试", null);
    }

    @Override
    public Result getOrderShipperCode(String shipSn) {
        return new Result(false, StatusCode.RESERROR, "网络开小差了，请重试", null);
    }
}
