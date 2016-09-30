package com.dongdao.meetingmanager.info;

import java.io.Serializable;

import static com.dongdao.meetingmanager.R.id.room;

/**
 * Created by Administrator on 2016/9/28.
 */

public class MeetRoominfo implements Serializable{
    private int id;
    private String meetingroom;

    public MeetRoominfo() {
    }

    public MeetRoominfo(String meetingroom) {
        this.meetingroom = meetingroom;
    }

    public String getMeetingroom() {
        return meetingroom;
    }

    public void setMeetingroom(String meetingroom) {
        this.meetingroom = meetingroom;
    }

    @Override
    public String toString() {
        return "MeetRoominfo{" +
                "meetingroom='" + meetingroom + '\'' +
                '}';
    }
}
