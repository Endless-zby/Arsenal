package com.zby.dao;

import com.zby.entity.Arms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ArmsDao extends JpaRepository<Arms,String>, JpaSpecificationExecutor<Arms> {



}
