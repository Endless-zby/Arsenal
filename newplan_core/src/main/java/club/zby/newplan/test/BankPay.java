package club.zby.newplan.test;

/**
 * @Author: 赵博雅
 * @Date: 2019/12/2 12:42
 */
public class BankPay implements PayMode {
    @Override
    public String PayModeType() {
        return "银联支付。。。。";
    }
}
