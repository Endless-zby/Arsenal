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

    private String remark;

    public Red(String id, String starttime, String outtime, String remark) {
        this.id = id;
        this.starttime = starttime;
        this.outtime = outtime;
        this.remark = remark;
    }
    public Red(String starttime, String outtime, String remark) {
        this.starttime = starttime;
        this.outtime = outtime;
        this.remark = remark;
    }
    public Red() {

    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
