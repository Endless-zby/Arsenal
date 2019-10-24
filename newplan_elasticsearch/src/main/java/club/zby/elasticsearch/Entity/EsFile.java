package club.zby.elasticsearch.Entity;


import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import javax.persistence.Id;

import java.io.Serializable;


/**
 * @Author: 赵博雅
 * @Date: 2019/10/24 9:40
 */
@Document(indexName = "ftpfile",type = "filename")
public class EsFile implements Serializable {
    @Field(index=true ,analyzer = "ik_max_word"   ,searchAnalyzer = "ik_max_word")
    private String userid;
    @Id
    private String id;
    @Field(index=true ,analyzer = "ik_max_word"   ,searchAnalyzer = "ik_max_word")
    private String filename;
    @Field(index=true ,analyzer = "ik_max_word"   ,searchAnalyzer = "ik_max_word")
    private Long filesize;
    @Field(index=true ,analyzer = "ik_max_word"   ,searchAnalyzer = "ik_max_word")
    private int filedownnum;
    @Field(index=true ,analyzer = "ik_max_word"   ,searchAnalyzer = "ik_max_word")
    private Long timestamp;
    @Field(index=true ,analyzer = "ik_max_word"   ,searchAnalyzer = "ik_max_word")
    private String filepath;
    @Field(index=true ,analyzer = "ik_max_word"   ,searchAnalyzer = "ik_max_word")
    private int filetag;

    public EsFile(String userid, String id, String filename, Long filesize, int filedownnum, Long timestamp, String filepath, int filetag) {
        this.userid = userid;
        this.id = id;
        this.filename = filename;
        this.filesize = filesize;
        this.filedownnum = filedownnum;
        this.timestamp = timestamp;
        this.filepath = filepath;
        this.filetag = filetag;
    }

    public EsFile(String id, String filename, Long filesize, int filedownnum, Long timestamp, String filepath, int filetag) {
        this.id = id;
        this.filename = filename;
        this.filesize = filesize;
        this.filedownnum = filedownnum;
        this.timestamp = timestamp;
        this.filepath = filepath;
        this.filetag = filetag;
    }

    public EsFile() {
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

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
