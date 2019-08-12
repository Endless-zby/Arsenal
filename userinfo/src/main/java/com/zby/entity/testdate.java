package com.zby.entity;

import java.io.Serializable;

/**
 * @Author: 赵博雅
 * @Date: 2019/8/8 15:29
 */
public class testdate implements Serializable {

    private String start;
    private String end;

    public testdate(String start, String end) {
        this.start = start;
        this.end = end;
    }
    public testdate() {
    }
    @Override
    public String toString() {
        return "testdate{" +
                "start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }
}
