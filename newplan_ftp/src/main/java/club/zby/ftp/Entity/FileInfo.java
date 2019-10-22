package club.zby.ftp.Entity;


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

    @Id
    private String id;
    private String filename;
    private String filesize;
    private String filechmod;
    private String timestamp;
    private String filepath;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public String getFilechmod() {
        return filechmod;
    }

    public void setFilechmod(String filechmod) {
        this.filechmod = filechmod;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public FileInfo(String id, String filename, String filesize, String filechmod, String timestamp, String filepath) {
        this.id = id;
        this.filename = filename;
        this.filesize = filesize;
        this.filechmod = filechmod;
        this.timestamp = timestamp;
        this.filepath = filepath;
    }
    public FileInfo(String filename, String filesize, String filechmod, String timestamp, String filepath) {
        this.filename = filename;
        this.filesize = filesize;
        this.filechmod = filechmod;
        this.timestamp = timestamp;
        this.filepath = filepath;
    }
    public FileInfo() {
    }
}
