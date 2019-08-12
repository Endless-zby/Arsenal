package com.zby.dao;

import com.zby.entity.myTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: 赵博雅
 * @Date: 2019/8/9 9:55
 */
public interface timeDao extends JpaRepository<myTime,String>, JpaSpecificationExecutor<myTime> {



}
