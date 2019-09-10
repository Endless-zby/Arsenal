package club.zby.newplan.Dao;

import club.zby.newplan.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {

    @Modifying
    @Query(nativeQuery = true,value = "update tb_user f set f.status = '1' where f.Id = ?")
    void updateStatus(String id);//注册状态   0:未激活 1:激活
    User findAllById(String id);
    boolean existsByPhone(String phone);
    User findByUsername(String username);
    boolean existsByUsername(String username);

}
