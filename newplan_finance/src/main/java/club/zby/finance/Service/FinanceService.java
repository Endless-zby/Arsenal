package club.zby.finance.Service;

import club.zby.finance.Config.Result;
import club.zby.finance.Config.StatusCode;
import club.zby.finance.Dao.FinanceDao;
import club.zby.finance.Entity.Finance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FinanceService {

    @Autowired
    private FinanceDao financeDao;

    /**
     * 根据who id查询账单记录
     * @param who
     * @return
     */
    @Transactional
    public List<Finance> findAllByWho(String who){
        return financeDao.findAllByWho(who);
    }

    public List<String> findIdByWho(String who){
        return financeDao.findIdByWho(who);
    }

    /**
     * 提交记录
     * @param finance
     * @return
     */
    @Transactional
    public Finance saveFinance(Finance finance){
        return financeDao.save(finance);
    }

    /**
     * 删除记录
     * @param id
     */
    @Transactional
    public int delFinance(String id){
        return financeDao.delById(id);
    }

    @Transactional
    public Result findAllByid(String userid){
        //前端数据处理太慢，放到后端，但是保留前端处理逻辑，必要时候切换逻辑处理的时机
        List<Finance> allfinance = financeDao.findAllByWho(userid);
        if(allfinance.size() == 0){
            return new Result(false, StatusCode.RESERROR,"您还没有过数据哦！",null);
        }
        //找出所有的分类

        HashMap<String, BigDecimal> map = new HashMap<String, BigDecimal>();
        //每次循环顺便计算总金额
        BigDecimal money = new BigDecimal("0");
        for (Finance finance : allfinance) {
            money = money.add(finance.getMoney());//总金额
            if(map.containsKey(finance.getPurpose())){//已有该分类？值加
                BigDecimal bigDecimal = map.get(finance.getPurpose());
                map.put(finance.getPurpose(),bigDecimal.add(finance.getMoney()));
            }else {//没有该分类，创建分类
                map.put(finance.getPurpose(),finance.getMoney());
            }
        }
        //massage中暂时存放总金额
        return new Result(true,StatusCode.OK,money.toString(),map);
        //
    }
}
