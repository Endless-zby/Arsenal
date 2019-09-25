package club.zby.newplan.controller.expressclient;

import club.zby.newplan.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: 赵博雅
 * @Date: 2019/9/25 11:33
 */

@Component
@FeignClient(name = "express",fallback = ExpressClientImp.class)//uereka中的注册服务名，
public interface ExpressClient {

    //根据物流单号和快递公司编码查询物流信息
    @GetMapping(value = "Express/getOrderTracesByJson")
    Result getOrderTracesByJson(@RequestParam("shipChannel") String shipChannel, @RequestParam("shipSn") String shipSn);

    //根据物流单号查询快递公司编码
    @GetMapping(value = "Express/getOrderShipperCode")
    Result getOrderShipperCode(@RequestParam("shipSn") String shipSn);

}
