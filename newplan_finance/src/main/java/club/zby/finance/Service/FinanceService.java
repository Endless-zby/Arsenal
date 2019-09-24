package club.zby.finance.Service;

import club.zby.finance.Dao.FinanceDao;
import club.zby.finance.Entity.Finance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    /**
     * 提交记录
     * @param finance
     * @return
     */
    @Transactional
    public Finance saveFinance(Finance finance){
        return financeDao.save(finance);
    }

}
