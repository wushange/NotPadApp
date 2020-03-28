package com.demo.notpadapp.bean;

import com.blankj.utilcode.util.TimeUtils;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class Income extends LitePalSupport implements Serializable {
    private String iid;
    private String uid;
    private String mark;
    private String type;
    private String payer;
    private long time;
    private String timeStr;
    private int money;

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
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

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
        this.time = TimeUtils.string2Millis(timeStr, "yyyy-MM-dd");
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
