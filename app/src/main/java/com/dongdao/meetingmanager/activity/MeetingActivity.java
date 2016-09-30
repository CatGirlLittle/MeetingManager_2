package com.dongdao.meetingmanager.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dongdao.meetingmanager.R;
import com.dongdao.meetingmanager.adapter.MeetingAdapter;
import com.dongdao.meetingmanager.http.HttpUrl;
import com.dongdao.meetingmanager.http.HttpUtils;
import com.dongdao.meetingmanager.http.MyBitmapCallBack;
import com.dongdao.meetingmanager.http.MyCallBackHandle;
import com.dongdao.meetingmanager.http.MyStringCallBack;
import com.dongdao.meetingmanager.info.MeetRoominfo;
import com.dongdao.meetingmanager.info.Meetinginfo;
import com.dongdao.meetingmanager.info.Pic;
import com.dongdao.meetingmanager.service.MyService;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Request;

import static com.dongdao.meetingmanager.R.id.myviewflipper;
import static com.dongdao.meetingmanager.R.id.rightmeetingroom;

/**
 * Created by Administrator on 2016/9/18.
 */
public class MeetingActivity extends FragmentActivity implements MyCallBackHandle{
    private List<Meetinginfo> mMeetinginfos,nowMeetinginfos=new ArrayList<Meetinginfo>();
    private Meetinginfo mMeetinginfo;
    private List<MeetRoominfo> mRoominfos=new ArrayList<MeetRoominfo>();
    private MeetingAdapter mAdapter;
    private ListView mListView;
    private ViewFlipper mFlipper;
    private MyStringCallBack mBack;
    private MyBitmapCallBack mBitmapCallBack;
    private TextView textView, nowroom,nowtheme,nowtime,nowuser;
    private LinearLayout mLayout;
    private MsgReceiver msgReceiver;
    private List<Pic> mPics=new ArrayList<>();
    private BitmapUtils mUtils;

    //天气接口
    //TextView滚动

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_meeting_list);
        mUtils=new BitmapUtils(this);
        initView();
        initData();
        initAdapter();
        initListener();
    }
    //初始化数据 通过网络请求
    private void initData() {
        msgReceiver = new MsgReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.communication.RECEIVER");
        registerReceiver(msgReceiver, intentFilter);
        Intent intent=new Intent(this,MyService.class);
        startService(intent);
        getPic();
    }

    private void initAdapter() {
    }

    private void initListener() {

    }
    //初始化控件
    private void initView() {
        mListView= (ListView) this.findViewById(R.id.meetings);
        mFlipper= (ViewFlipper) this.findViewById(myviewflipper);
        textView= (TextView) this.findViewById(R.id.tips);
        nowroom= (TextView) this.findViewById(R.id.nowroom);
        nowroom.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
        nowtheme= (TextView) this.findViewById(R.id.nowtheme);
        nowtime= (TextView) this.findViewById(R.id.nowtime);
        nowuser= (TextView) this.findViewById(R.id.nowuser);
        //mFlipper.startFlipping();
        mLayout= (LinearLayout) this.findViewById(R.id.nowbg);
        mLayout.setBackgroundResource(R.drawable.rightmeeting);



    }

    @Override
    public void OnError(Call call, int i, Exception e) {

    }

    @Override
    public void onResponse(Object s, int i) {
       switch (i){
           case 1:
              // parse(s);
               break;
           case 2:
               parse(s.toString(),2);
               break;
       }
    }

    @Override
    public void onBefore(Request request, int id) {
    }

    @Override
    public void onAfter(int id) {

    }

    @Override
    public void inProgress(float progress, long total, int id) {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(msgReceiver);
        mUtils=null;
       /* mMeetinginfos.clear();
        mRoominfos.clear();
        nowMeetinginfos.clear();
        mMeetinginfo=null;*/

    }

    //JSON 解析
    private void parse(String s,int id){
        /*JSONObject object= JSON.parseObject(s);
        JSONObject result=object.getJSONObject("result");
        JSONObject allMeeting=result.getJSONObject("allMeeting");
        JSONArray freeRoom=result.getJSONArray("freeRoom");
        JSONArray nowMeeting=result.getJSONArray("nowMeeting");
        JSONArray list=allMeeting.getJSONArray("list");
        nowMeetinginfos=JSON.parseArray(nowMeeting.toString(),Meetinginfo.class);
        mMeetinginfo=nowMeetinginfos.get(0);
        nowroom.setText(mMeetinginfo.getMeetingroom()+"会议室会议");
        nowtheme.setText(mMeetinginfo.getMeetingtheme());
        nowtime.setText(mMeetinginfo.getBegintime().substring(0,5)+"--"+mMeetinginfo.getEndtime().substring(0,5));
        nowuser.setText(mMeetinginfo.getMeetinguser());
        mMeetinginfos=JSON.parseArray(list.toString(),Meetinginfo.class);
        mRoominfos=JSON.parseArray(freeRoom.toString(),MeetRoominfo.class);
       // initallList(mRoominfos,mMeetinginfos);*/
        switch (id){
            case 1:
                break;
            case 2:
                JSONObject object= JSON.parseObject(s);
                JSONObject result=object.getJSONObject("result");
                JSONArray picList=result.getJSONArray("picList");
                mPics=JSON.parseArray(picList.toString(),Pic.class);
                for(int i=0;i<mPics.size();i++){
                    String url=mPics.get(i).getPicpath();
                    ImageView view=new ImageView(this);
                    view.setAdjustViewBounds(true);
                    mUtils.display(view,url);
                    mFlipper.addView(view);
                }
                mFlipper.startFlipping();
                break;
        }

    }
    class  MsgReceiver extends BroadcastReceiver {

        public MsgReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            nowMeetinginfos= (List<Meetinginfo>) intent.getSerializableExtra("nowmeeting");
            if(nowMeetinginfos.size()<=0){
                nowroom.setText("当前室会议");
                nowtheme.setText("空闲");
                nowtime.setText("空闲");
                nowuser.setText("空闲");
            }else {
                mMeetinginfo = nowMeetinginfos.get(0);
                nowroom.setText(mMeetinginfo.getMeetingroom() + "会议室会议");
                nowtheme.setText(mMeetinginfo.getMeetingtheme());
                nowtime.setText(mMeetinginfo.getBegintime().substring(0, 5) + "--" + mMeetinginfo.getEndtime().substring(0, 5));
                nowuser.setText(mMeetinginfo.getMeetinguser());
            }
            mAdapter=new MeetingAdapter((List<Meetinginfo>) intent.getSerializableExtra("meetings"),MeetingActivity.this);
            mListView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
           // parse(intent.getStringExtra("progress"));
        }
    }
    private void getPic(){
        mBack=new MyStringCallBack(this);
        HttpUtils.post("http://192.168.1.76:8702/cfpic/allpic.do",2,null,mBack);
    }

}
