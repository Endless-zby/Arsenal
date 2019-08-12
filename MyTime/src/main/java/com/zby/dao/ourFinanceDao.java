package com.zby.dao;


import com.zby.entity.ourFinance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * @Author: 赵博雅
 * @Date: 2019/8/9 18:06
 */
public interface ourFinanceDao extends JpaRepository<ourFinance,String>, JpaSpecificationExecutor<ourFinance> {


}
