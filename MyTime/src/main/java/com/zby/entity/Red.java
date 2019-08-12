package com.zby.entity;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: 赵博雅
 * @Date: 2019/8/9 10:22
 */
@Entity
@Table(name = "tb_red")
public class Red implements Serializable {

    @Id
    private String id;

    private String starttime;

    private String outtime;

    public Red(String id, String starttime, String outtime) {
        this.id = id;
        this.starttime = starttime;
        this.outtime = outtime;
    }

    public Red(String starttime, String outtime) {
        this.starttime = starttime;
        this.outtime = outtime;
    }

    public Red() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getOuttime() {
        return outtime;
    }

    public void setOuttime(String outtime) {
        this.outtime = outtime;
    }
}
