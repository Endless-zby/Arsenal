package com.zby.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * @Author: 赵博雅
 * @Date: 2019/8/9 9:54
 */
@Entity
@Table(name="tb_sleep")
public class myTime implements Serializable {

    @Id
    private String id;
    private String appDate;
    private String appTime;

    public myTime(String id, String appDate, String appTime) {
        this.id = id;
        this.appDate = appDate;
        this.appTime = appTime;
    }
    public myTime(String appDate, String appTime) {
        this.appDate = appDate;
        this.appTime = appTime;
    }
    public myTime() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    public String getAppTime() {
        return appTime;
    }

    public void setAppTime(String appTime) {
        this.appTime = appTime;
    }

    @Override
    public String toString() {
        return "myTime{" +
                "id='" + id + '\'' +
                ", appDate='" + appDate + '\'' +
                ", appTime='" + appTime + '\'' +
                '}';
    }
}
