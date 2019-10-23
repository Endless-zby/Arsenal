package club.zby.ftp.Entity;


import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: 赵博雅
 * @Date: 2019/10/22 18:27
 */
@Entity
@Table(name = "tb_file")
public class FileInfo implements Serializable {


    private String userid;
    @Id
    private String fileid;
    private String filename;
    private Long filesize;
    private int filedownnum;
    private Long timestamp;
    private String filepath;
    private int filetag;

    public FileInfo(String userid, String fileid, String filename, Long filesize, int filedownnum, Long timestamp, String filepath, int filetag) {
        this.userid = userid;
        this.fileid = fileid;
        this.filename = filename;
        this.filesize = filesize;
        this.filedownnum = filedownnum;
        this.timestamp = timestamp;
        this.filepath = filepath;
        this.filetag = filetag;
    }

    public FileInfo(String fileid, String filename, Long filesize, int filedownnum, Long timestamp, String filepath, int filetag) {
        this.fileid = fileid;
        this.filename = filename;
        this.filesize = filesize;
        this.filedownnum = filedownnum;
        this.timestamp = timestamp;
        this.filepath = filepath;
        this.filetag = filetag;
    }

    public FileInfo() {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Long getFilesize() {
        return filesize;
    }

    public void setFilesize(Long filesize) {
        this.filesize = filesize;
    }

    public int getFiledownnum() {
        return filedownnum;
    }

    public void setFiledownnum(int filedownnum) {
        this.filedownnum = filedownnum;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public int getFiletag() {
        return filetag;
    }

    public void setFiletag(int filetag) {
        this.filetag = filetag;
    }
}
