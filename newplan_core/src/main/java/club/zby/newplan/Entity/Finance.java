package club.zby.newplan.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tb_finance")
@IdClass(Finance.class)
public class Finance implements Serializable {
    @Id
    private String id;
    private BigDecimal money;
    private String purpose;
    private String remark;
    private int status;
    @Id
    private String who;
    private String tag1;
    private Date time;

    public Finance(String id, BigDecimal money, String purpose, String remark, int status, String who, String tag1, Date time) {
        this.id = id;
        this.money = money;
        this.purpose = purpose;
        this.remark = remark;
        this.status = status;
        this.who = who;
        this.tag1 = tag1;
        this.time = time;
    }

    public Finance() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
