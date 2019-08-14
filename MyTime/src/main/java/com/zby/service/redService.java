package com.zby.service;

import com.zby.config.IdWorker;
import com.zby.dao.ourFinanceDao;
import com.zby.dao.redDao;
import com.zby.entity.Red;
import com.zby.entity.myTime;
import com.zby.entity.ourFinance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author: 赵博雅
 * @Date: 2019/8/12 9:22
 */

@Service
public class redService {

    @Autowired
    private redDao reddao;
    @Autowired
    private IdWorker idWorker;

    @Transactional
    public Red red(Red red){

        red.setId(String.valueOf(idWorker.nextId()));

        Red save = reddao.save(red);

        return save;
    }

    @Transactional
    public List<Red> queryred(){

        List<Red> all = reddao.findAll();

        return all;
    }

    @Transactional
    public boolean deletered(Red red){

        reddao.deleteById(red.getId());

        return true;
    }

    @Transactional
    public Page<Red> red1(int start, int pageSize){
        PageRequest pagerequest = PageRequest.of(start-1, pageSize);
        Page<Red> Reds = reddao.findAll(pagerequest);

        return Reds;
    }


}
