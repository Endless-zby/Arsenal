package club.zby.newplan.test;

/**
 * @Author: 赵博雅
 * @Date: 2019/12/2 10:05
 */

public enum PayEnum {
    ALI_PAY ("club.zby.newplan.test.ALiPay"),
    WEI_PAY ("club.zby.newplan.test.WeiChatPay");

    /**
     * 策略模式  =  工厂模式 + 枚举类型
     * 目的：解决多个if问题
     * @param className
     */
    PayEnum(String className){
        this.setClassName(className);
    }

    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
