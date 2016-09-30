package com.dongdao.meetingmanager.info;

import java.io.Serializable;

import static com.dongdao.meetingmanager.R.id.date;
import static com.dongdao.meetingmanager.R.id.people;

/**
 * Created by Administrator on 2016/9/12.
 * 会议实体类
 */
public class Meetinginfo implements Serializable{
    /*编号
            会议室编号
    会议室议题
            日期
    开始时间
            结束时间
    使用者
    会议室使用状态（使用中、预订）
    备注
            时间戳*/
    private int id;
    private String meetingroom;
    private String meetingtheme;
    private String meetingtime;
    private String meetinguser;
    private String begintime;
    private String endtime;
    private int usestatus;//1 在使用 0 空闲

    public Meetinginfo() {
    }

    public Meetinginfo(String meetingroom, String meetingtheme, String meetingtime, String meetinguser, String begintime, String endtime, int usestatus) {
        this.meetingroom = meetingroom;
        this.meetingtheme = meetingtheme;
        this.meetingtime = meetingtime;
        this.meetinguser = meetinguser;
        this.begintime = begintime;
        this.endtime = endtime;
        this.usestatus = usestatus;
    }

    public String getMeetingroom() {
        return meetingroom;
    }

    public void setMeetingroom(String meetingroom) {
        this.meetingroom = meetingroom;
    }

    public String getMeetingtheme() {
        return meetingtheme;
    }

    public void setMeetingtheme(String meetingtheme) {
        this.meetingtheme = meetingtheme;
    }

    public String getMeetingtime() {
        return meetingtime;
    }

    public void setMeetingtime(String meetingtime) {
        this.meetingtime = meetingtime;
    }

    public String getMeetinguser() {
        return meetinguser;
    }

    public void setMeetinguser(String meetinguser) {
        this.meetinguser = meetinguser;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public int getUsestatus() {
        return usestatus;
    }

    public void setUsestatus(int usestatus) {
        this.usestatus = usestatus;
    }

    @Override
    public String toString() {
        return "Meetinginfo{" +
                "meetingroom='" + meetingroom + '\'' +
                ", meetingtheme='" + meetingtheme + '\'' +
                ", meetingtime='" + meetingtime + '\'' +
                ", meetinguser='" + meetinguser + '\'' +
                ", begintime='" + begintime + '\'' +
                ", endtime='" + endtime + '\'' +
                ", usestatus=" + usestatus +
                '}';
    }
}
