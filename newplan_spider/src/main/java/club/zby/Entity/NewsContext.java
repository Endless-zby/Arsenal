package club.zby.Entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/31 16:25
 */
public class NewsContext implements Serializable {

    private int id;  //自增id
    private Date releaseTime;   //爬取时间
    private String title;   //标题
    private String context; //内容
    private String url; //地址
    private String sendtime; //发稿时间
    private String editor; //责任编辑
    private String author; //作者

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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
