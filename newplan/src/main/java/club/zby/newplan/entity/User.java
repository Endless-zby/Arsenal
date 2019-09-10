package club.zby.newplan.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tb_user")
public class User implements Serializable {
    @Id
    private String id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String token;
    private Date regtime;   //注册日期
    private Date updatetime;    //更新日期，最近登录时间
    private String checkmethod;     //注册验证方式    0:手机 1:邮件
    private String status;      //注册状态   0:未激活 1:激活
    private String type;        //账号类型   0:用户   1:管理员

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    public Date getUpdate() {
        return updatetime;
    }

    public void setUpdate(Date update) {
        this.updatetime = update;
    }

    public String getCheckmethod() {
        return checkmethod;
    }

    public void setCheckmethod(String checkmethod) {
        this.checkmethod = checkmethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User(String id, String username, String password, String phone, String email, String token, Date regtime, Date update, String checkmethod, String status, String type) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.token = token;
        this.regtime = regtime;
        this.updatetime = update;
        this.checkmethod = checkmethod;
        this.status = status;
        this.type = type;
    }

    public User(String username, String password, String phone, String email, String token, Date regtime, Date update, String checkmethod, String status, String type) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.token = token;
        this.regtime = regtime;
        this.updatetime = update;
        this.checkmethod = checkmethod;
        this.status = status;
        this.type = type;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {

    }
}
