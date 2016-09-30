package com.dongdao.meetingmanager.info;

/**
 * Created by Administrator on 2016/9/30.
 */

public class Pic {
    int id;
    String picpath;

    public Pic() {
    }

    public Pic(String picpath) {
        this.picpath = picpath;
    }

    public String getPicpath() {
        return picpath;
    }

    public void setPicpath(String picpath) {
        this.picpath = picpath;
    }

    @Override
    public String toString() {
        return "Pic{" +
                "id=" + id +
                ", picpath='" + picpath + '\'' +
                '}';
    }
}
