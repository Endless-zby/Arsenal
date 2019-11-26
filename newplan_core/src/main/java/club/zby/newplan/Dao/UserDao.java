package club.zby.newplan.Dao;

import club.zby.newplan.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface UserDao extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {

    @Modifying
    @Query(nativeQuery = true,value = "update tb_user f set f.status = '1' where f.Id = ?")
    void updateStatus(String id);//注册状态   0:未激活 1:激活
    User findAllById(String id);
    boolean existsByPhone(String phone);
    User findByUsername(String username);
    boolean existsByUsername(String username);
    User findAllByQqopenid(String openid);
    boolean existsById(String id);

    @Modifying
    @Query(nativeQuery = true,value = "update tb_user f set f.username = ?1,f.password = ?2,f.phone = ?3,f.email = ?4,f.regtime = ?5,f.updatetime = ?6,f.checkmethod = ?7,f.status = ?8,f.type = ?9 where f.Id = ?10")
    int updateInfoById(String username, String password, String phone, String email, Date regtime, Date updatetime, String checkmethod, String status, String type, String id);


    @Modifying
    @Query(nativeQuery = true,value = "update tb_user f set f.username = ?1,f.password = ?1,f.phone = ?1 where f.Id = ?2")
    int updatePhoneById(String phone, String id);

    /**
     * 不带手机号
     */
    @Modifying
    @Query(nativeQuery = true,value = "update tb_user f set f.username = ?1,f.nickname = ?2,f.gender = ?3,f.email = ?4,f.updatetime = ?5 where f.Id = ?6")
    int UpDataInfo(String username,String nickname,String gender,String email,Date time,String id);


    /**
     * 带手机号
     */
    @Modifying
    @Query(nativeQuery = true,value = "update tb_user f set f.username = ?1,f.phone = ?2,f.nickname = ?3,f.gender = ?4,f.email = ?5,f.updatetime = ?6 where f.Id = ?7")
    int updateUserInfoById(String username,String phone,String nickname,String gender,String email,Date time,String id);


}
