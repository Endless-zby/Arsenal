package club.zby.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/25 15:12
 */
@Entity
@Table(name = "tb_spider_list")
public class SpiderData {

    @Id
    private int id;
    private Date releaseTime;
    private String title;
    private Date crawlTime;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCrawlTime() {
        return crawlTime;
    }

    public void setCrawlTime(Date crawlTime) {
        this.crawlTime = crawlTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public SpiderData(int id, Date releaseTime, String title, Date crawlTime, String url) {
        this.id = id;
        this.releaseTime = releaseTime;
        this.title = title;
        this.crawlTime = crawlTime;
        this.url = url;
    }
}
