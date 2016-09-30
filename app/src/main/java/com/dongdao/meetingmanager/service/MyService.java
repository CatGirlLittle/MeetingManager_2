package com.dongdao.meetingmanager.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dongdao.meetingmanager.activity.MeetingActivity;
import com.dongdao.meetingmanager.adapter.MeetingAdapter;
import com.dongdao.meetingmanager.http.HttpUrl;
import com.dongdao.meetingmanager.http.HttpUtils;
import com.dongdao.meetingmanager.http.MyCallBackHandle;
import com.dongdao.meetingmanager.http.MyStringCallBack;
import com.dongdao.meetingmanager.info.MeetRoominfo;
import com.dongdao.meetingmanager.info.Meetinginfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Request;

import static android.os.Build.VERSION_CODES.M;
import static com.dongdao.meetingmanager.R.id.nowroom;
import static com.dongdao.meetingmanager.R.id.nowtheme;
import static com.dongdao.meetingmanager.R.id.nowtime;
import static com.dongdao.meetingmanager.R.id.nowuser;

public class MyService extends Service implements MyCallBackHandle{
    private List<Meetinginfo> mMeetinginfos,nowMeetinginfos=new ArrayList<Meetinginfo>();
    private Meetinginfo mMeetinginfo;
    private List<MeetRoominfo> mRoominfos=new ArrayList<MeetRoominfo>();
    private MyStringCallBack mBack;
    private Intent intent = new Intent("com.example.communication.RECEIVER");
    private Intent beforeintent=new Intent("before");
    private Intent afterintent=new Intent("after");
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
        }, 1000,60*1000);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void OnError(Call call, int i, Exception e) {

    }

    @Override
    public void onResponse(Object s, int i) {
        parse(s.toString());
        intent.putExtra("meetings", (Serializable) mMeetinginfos);
        intent.putExtra("nowmeeting", (Serializable) nowMeetinginfos);
        sendBroadcast(intent);
    }

    @Override
    public void onBefore(Request request, int id) {
        sendBroadcast(beforeintent);
    }

    @Override
    public void onAfter(int id) {
        sendBroadcast(afterintent);
    }

    @Override
    public void inProgress(float progress, long total, int id) {
    }
    private void parse(String s){
        JSONObject object= JSON.parseObject(s);
        JSONObject result=object.getJSONObject("result");
        JSONObject allMeeting=result.getJSONObject("allMeeting");
        JSONArray freeRoom=result.getJSONArray("freeRoom");
        JSONArray nowMeeting=result.getJSONArray("nowMeeting");
        JSONArray list=allMeeting.getJSONArray("list");
        nowMeetinginfos=JSON.parseArray(nowMeeting.toString(),Meetinginfo.class);
       // nowroom.setText(mMeetinginfo.getMeetingroom()+"会议室会议");
       // nowtheme.setText(mMeetinginfo.getMeetingtheme());
        //nowtime.setText(mMeetinginfo.getBegintime().substring(0,5)+"--"+mMeetinginfo.getEndtime().substring(0,5));
       // nowuser.setText(mMeetinginfo.getMeetinguser());
        mMeetinginfos=JSON.parseArray(list.toString(),Meetinginfo.class);
        mRoominfos=JSON.parseArray(freeRoom.toString(),MeetRoominfo.class);
        initallList(mRoominfos,mMeetinginfos);
       // mAdapter=new MeetingAdapter(mMeetinginfos,MeetingActivity.this);
       // mListView.setAdapter(mAdapter);
       // mAdapter.notifyDataSetChanged();
    }
    private List<Meetinginfo> initallList(List<MeetRoominfo> roominfos,List<Meetinginfo> meetinginfos) {
        for(int i=0;i<roominfos.size();i++){
            //meetingroom, String meetingtheme, String meetingtime, String meetinguser, String begintime, String endtime, int usestatus
            mMeetinginfo=new Meetinginfo(roominfos.get(i).getMeetingroom(),"","","","","",0);
            meetinginfos.add(0, mMeetinginfo);
        }
        return meetinginfos;
    }
}
