package com.thssh.recycler;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/20
 */

public class MainBean {

    private String title;
    private String des;

    public MainBean() {
    }

    public MainBean(String title, String des) {
        this.title = title;
        this.des = des;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getTitle() {
        return title;
    }

    public String getDes() {
        return des;
    }

    @Override
    public String toString() {
        return "MainBean{" +
                "title='" + title + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
