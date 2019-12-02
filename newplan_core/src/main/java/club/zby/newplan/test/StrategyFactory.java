package club.zby.newplan.test;

import org.springframework.stereotype.Component;

/**
 * @Author: 赵博雅
 * @Date: 2019/12/2 10:15
 */
@Component
public class StrategyFactory {

    public PayMode getStrategyFactory(String Code) throws ClassNotFoundException {

        String className = PayEnum.valueOf(Code).getClassName();

        try {
            return (PayMode) Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
