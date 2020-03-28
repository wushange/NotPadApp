package com.demo.notpadapp.bean;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class Mark extends LitePalSupport implements Serializable {
    private String mid;
    private String uid;
    private String mark;
    private long time;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
