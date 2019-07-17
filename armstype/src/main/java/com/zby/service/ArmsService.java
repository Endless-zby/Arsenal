package com.zby.service;


import com.zby.dao.ArmsDao;
import com.zby.entity.Arms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 */
@Service
@Transactional
public class ArmsService {

    @Autowired
    private ArmsDao armsDao;

    public List<Arms> Initialization(){

        List<Arms> all = armsDao.findAll();

        return all;
    }
}
