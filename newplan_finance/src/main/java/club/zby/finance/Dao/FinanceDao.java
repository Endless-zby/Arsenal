package club.zby.finance.Dao;

import club.zby.finance.Entity.Finance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FinanceDao extends JpaRepository<Finance,String>, JpaSpecificationExecutor<Finance> {

    /**
     * 查用户记录列表
     * @param who
     * @return
     */
    @Modifying
    @Query(nativeQuery = true,value = "select * from tb_finance where who = ? order by time ")
    List<Finance> findAllByWho(String who);

    /**
     * 分页查询
     * @param who
     * @param pageRequest
     * @return
     */

    @Query(nativeQuery = true,value = "select * from tb_finance where who = ? order by time ")
    Page<Finance> selFinance(String who, PageRequest pageRequest);


    @Modifying
    @Query(nativeQuery = true,value = "select t.id from tb_finance t where who = ?")
    List<String> findIdByWho(String who);


    /**
     * 根据id删除一条数据
     * @param id
     * @return
     */
    @Modifying
    @Query(nativeQuery = true,value = "delete from tb_finance where id = ? ")
    int delById(String id);

    List<Finance> findAllById(String id);
}
