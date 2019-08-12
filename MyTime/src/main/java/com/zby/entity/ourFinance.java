package com.zby.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: 赵博雅
 * @Date: 2019/8/9 17:49
 */

@Entity
@Table(name = "tb_ourFinance")
public class ourFinance implements Serializable {

    @Id
    private String id;
    private int status;  //0代表支出  1代表收入
    private String time;
    private String purpose; //用途
    private Double money;
    private String remark;  //备注
    private String tag1;  //保留1
    private String tag2;  //保留2

    public ourFinance(String id, int status, String time, String purpose, Double money, String remark, String tag1, String tag2) {
        this.id = id;
        this.status = status;
        this.time = time;
        this.purpose = purpose;
        this.money = money;
        this.remark = remark;
        this.tag1 = tag1;
        this.tag2 = tag2;
    }
    public ourFinance(int status, String time, String purpose, Double money, String remark) {
        this.status = status;
        this.time = time;
        this.purpose = purpose;
        this.money = money;
        this.remark = remark;
    }
    public ourFinance(int status, String time, String purpose, Double money, String remark, String tag1, String tag2) {
        this.status = status;
        this.time = time;
        this.purpose = purpose;
        this.money = money;
        this.remark = remark;
        this.tag1 = tag1;
        this.tag2 = tag2;
    }
    public ourFinance() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }
}
