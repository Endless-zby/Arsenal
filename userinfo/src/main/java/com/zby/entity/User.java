package com.zby.entity;




import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_user")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private int type;
    private int age;
    private int sex;
    private boolean active; //活跃性

    public User(String id, String username, String password, String phone, String email, int type, int age, int sex, boolean active) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.type = type;
        this.age = age;
        this.sex = sex;
        this.active = active;
    }

    public User(String username, String password, String phone, String email, int type, int age, int sex, boolean active) {

        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.type = type;
        this.age = age;
        this.sex = sex;
        this.active = active;
    }

    public User() {

    }

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
