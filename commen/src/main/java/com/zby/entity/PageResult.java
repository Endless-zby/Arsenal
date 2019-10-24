package com.zby.entity;

import java.io.Serializable;

public class PageResult implements Serializable {

    private boolean flag ; //是否处理成功
    private int indexPage ;//当前页码
    private int totalPage ;//总页码
    private Object pagedata ;//返回数据

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getIndexPage() {
        return indexPage;
    }

    public void setIndexPage(Integer indexPage) {
        this.indexPage = indexPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Object getPagedata() {
        return pagedata;
    }

    public void setPagedata(Object pagedata) {
        this.pagedata = pagedata;
    }

    public PageResult(boolean flag, Integer indexPage, Integer totalPage, Object pagedata) {
        this.flag = flag;
        this.indexPage = indexPage;
        this.totalPage = totalPage;
        this.pagedata = pagedata;
    }

    public PageResult() {

    }
    public PageResult(Integer indexPage, Integer totalPage, Object pagedata) {
        this.indexPage = indexPage;
        this.totalPage = totalPage;
        this.pagedata = pagedata;
    }

}
