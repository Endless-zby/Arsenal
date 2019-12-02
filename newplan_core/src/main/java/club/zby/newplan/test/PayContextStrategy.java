package club.zby.newplan.test;

import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: 赵博雅
 * @Date: 2019/12/2 10:04
 */
@Component
public class PayContextStrategy {

    @Autowired
    private StrategyFactory strategyFactory;

    public String toPayHtml(String Code) throws ClassNotFoundException {
        if(StringUtil.isNullOrEmpty(Code)){
            return "参数错误。。。";
        }
        PayMode strategyFactory = this.strategyFactory.getStrategyFactory(Code);
        return strategyFactory.PayModeType();
    }

}
