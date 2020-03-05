package club.zby.excel.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: 赵博雅
 * @Date: 2020/3/5 22:48
 */
@Entity
@Table(name = "movieinfo")
public class Movieinfo implements Serializable {

    @Id
    private Integer movieid;
    private String moviename;
    private Date releasetime;
    private String director;
    private String leadactors;
    private String picture;
    private Double averating;
    private Integer numrating;
    private String description;
    private String typelist;

    public Integer getMovieid() {
        return movieid;
    }

    public void setMovieid(Integer movieid) {
        this.movieid = movieid;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

    public Date getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(Date releasetime) {
        this.releasetime = releasetime;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getLeadactors() {
        return leadactors;
    }

    public void setLeadactors(String leadactors) {
        this.leadactors = leadactors;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Double getAverating() {
        return averating;
    }

    public void setAverating(Double averating) {
        this.averating = averating;
    }

    public Integer getNumrating() {
        return numrating;
    }

    public void setNumrating(Integer numrating) {
        this.numrating = numrating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypelist() {
        return typelist;
    }

    public void setTypelist(String typelist) {
        this.typelist = typelist;
    }

    public Movieinfo() {

    }

    public Movieinfo(Integer movieid, String moviename, Date releasetime, String director, String leadactors, String picture, Double averating, Integer numrating, String description, String typelist) {
        this.movieid = movieid;
        this.moviename = moviename;
        this.releasetime = releasetime;
        this.director = director;
        this.leadactors = leadactors;
        this.picture = picture;
        this.averating = averating;
        this.numrating = numrating;
        this.description = description;
        this.typelist = typelist;
    }
}
