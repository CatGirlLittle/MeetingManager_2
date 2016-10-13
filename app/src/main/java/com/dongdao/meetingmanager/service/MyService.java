package com.dongdao.meetingmanager.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dongdao.meetingmanager.http.HttpUrl;
import com.dongdao.meetingmanager.http.HttpUtils;
import com.dongdao.meetingmanager.http.MyCallBackHandle;
import com.dongdao.meetingmanager.http.MyStringCallBack;
import com.dongdao.meetingmanager.info.MeetRoominfo;
import com.dongdao.meetingmanager.info.Meetinginfo;
import com.dongdao.meetingmanager.info.Temperature;
import com.dongdao.meetingmanager.info.Weather;
import com.dongdao.meetingmanager.info.Weatherpic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import okhttp3.Call;
import okhttp3.Request;

import static android.R.attr.data;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MyService extends Service implements MyCallBackHandle{
    private List<Meetinginfo> mMeetinginfos,nowMeetinginfos=new ArrayList<Meetinginfo>();
    private List<MeetRoominfo> mRoominfos=new ArrayList<MeetRoominfo>();
    private Meetinginfo mMeetinginfo;
    private Weatherpic mWeather;
    private String temp;
    private MyStringCallBack mBack;
    private Intent intent = new Intent("com.example.communication.RECEIVER");
    private Intent beforeintent=new Intent("before");
    private Intent weatherintent=new Intent("weather");
    private Intent errorintent=new Intent("error");

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the c
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                mBack=new MyStringCallBack(MyService.this);
                Map map = new HashMap();
                map.put(HttpUrl.sMEETINGROOM, "301");
                map.put(HttpUrl.sPAGE,"1");
                HttpUtils.post(HttpUrl.sHTPPHOST+HttpUrl.sMEETINGS, 1, map, mBack);
            }
        }, 1000,60*1000*15);
        Timer timer1=new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                mBack=new MyStringCallBack(MyService.this);
                HttpUtils.get(HttpUrl.sWEATHER,3,null,mBack);
            }
        }, 1000,60*1000*60);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void OnError(Call call, int i, Exception e) {
            sendBroadcast(errorintent);
    }

    @Override
    public void onResponse(Object s, int i) {
        switch (i){
            case 1:
                parse(s.toString(),1);
                intent.putExtra("meetings", (Serializable) mMeetinginfos);
                intent.putExtra("nowmeeting", (Serializable) nowMeetinginfos);
                sendBroadcast(intent);
                break;
            case 3:
                parse(s.toString(),3);
                weatherintent.putExtra("weather",mWeather);
                weatherintent.putExtra("tmp",temp);
                weatherintent.putExtra("cond",mWeather);
                sendBroadcast(weatherintent);
                break;
        }

    }

    @Override
    public void onBefore(Request request, int id) {
        switch (id){
            case 1:
                sendBroadcast(beforeintent);
                break;
        }
    }
    private void parse(String s,int index){
        switch (index){
            case 1:
                JSONObject object= JSON.parseObject(s);
                JSONObject result=object.getJSONObject("result");
                JSONObject allMeeting=result.getJSONObject("allMeeting");
                JSONArray freeRoom=result.getJSONArray("freeRoom");
                JSONArray nowMeeting=result.getJSONArray("nowMeeting");
                JSONArray list=allMeeting.getJSONArray("list");
                nowMeetinginfos=JSON.parseArray(nowMeeting.toString(),Meetinginfo.class);
                mMeetinginfos=JSON.parseArray(list.toString(),Meetinginfo.class);
                mRoominfos=JSON.parseArray(freeRoom.toString(),MeetRoominfo.class);
                initallList(mRoominfos,mMeetinginfos);
                break;
            case 3:

                JSONObject weather=JSON.parseObject(s);
                JSONArray heWeather=weather.getJSONArray("HeWeather data service 3.0");
                JSONObject weatherobj=heWeather.getJSONObject(0);
                JSONObject nowWeather=weatherobj.getJSONObject("now");
                JSONObject cond=nowWeather.getJSONObject("cond");
                mWeather=JSON.parseObject(cond.toString(),Weatherpic.class);
                temp=nowWeather.getString("tmp");
                break;
        }

    }
    private List<Meetinginfo> initallList(List<MeetRoominfo> roominfos,List<Meetinginfo> meetinginfos) {
        for(int i=0;i<roominfos.size();i++){
            mMeetinginfo=new Meetinginfo(roominfos.get(i).getMeetingroom(),"","","","","",0);
            meetinginfos.add(0, mMeetinginfo);
        }
        return meetinginfos;
    }
}
