package com.zby.service;

import com.zby.config.IdWorker;
import com.zby.dao.timeDao;
import com.zby.entity.myTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author: 赵博雅
 * @Date: 2019/8/9 9:56
 */

@Service
public class timeService {

    @Autowired
    private timeDao timedao;
    @Autowired
    private IdWorker idWorker;

@Transactional
    public myTime savedate(myTime mytime){

        mytime.setId(String.valueOf(idWorker.nextId()));
        System.out.println(mytime.toString());
        myTime save = timedao.save(mytime);
        return save;
    }
    public List<myTime> splinedate(){

        List<myTime> all = timedao.findAll();

        return all;
    }


}
