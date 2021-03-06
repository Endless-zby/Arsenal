package com.zby.service;


import com.zby.config.IdWorker;
import com.zby.dao.ourFinanceDao;
import com.zby.dao.timeDao;
import com.zby.entity.ourFinance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @Author: 赵博雅
 * @Date: 2019/8/9 19:42
 */
@Service
public class financeService {

    @Autowired
    private ourFinanceDao ourfinancedao;
    @Autowired
    private IdWorker idWorker;

    @Transactional
    public ourFinance finance(ourFinance ourfinance){

        ourfinance.setId(String.valueOf(idWorker.nextId()));

        ourFinance save = ourfinancedao.save(ourfinance);

        return save;
    }

    @Transactional
    public List<ourFinance> queryfinance(){

        List<ourFinance> all = ourfinancedao.findAll();

        System.out.println(all.toString());

        return all;
    }


    @Transactional
    public Page<ourFinance> queryfinance1(int start,int pageSize){
        PageRequest pagerequest = PageRequest.of(start-1, pageSize);
        Page<ourFinance> all = ourfinancedao.findAll(pagerequest);
        return all;
    }

    @Transactional
    public boolean delete(ourFinance ourfinance){

        ourfinancedao.deleteById(ourfinance.getId());

        return true;
    }

}
