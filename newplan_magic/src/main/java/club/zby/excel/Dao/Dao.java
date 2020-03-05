package club.zby.excel.Dao;
import club.zby.excel.entity.Movieinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * @Author: 赵博雅
 * @Date: 2020/3/5 22:41
 */
public interface Dao extends JpaRepository<Movieinfo,String>, JpaSpecificationExecutor<Movieinfo> {
    @Query(nativeQuery = true,value = "select COUNT(*) from movieinfo")
    int qerAll();
}
