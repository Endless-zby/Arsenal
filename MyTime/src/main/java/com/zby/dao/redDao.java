package com.zby.dao;

import com.zby.entity.Red;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: 赵博雅
 * @Date: 2019/8/12 9:19
 */
public interface redDao extends JpaRepository<Red,String>, JpaSpecificationExecutor<Red> {


}
