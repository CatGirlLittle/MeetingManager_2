package com.dongdao.meetingmanager.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dongdao.meetingmanager.R;
import com.dongdao.meetingmanager.adapter.MeetingAdapter;
import com.dongdao.meetingmanager.http.HttpUtils;
import com.dongdao.meetingmanager.http.MyCallBackHandle;
import com.dongdao.meetingmanager.http.MyStringCallBack;
import com.dongdao.meetingmanager.info.Meetinginfo;
import com.dongdao.meetingmanager.info.Pic;
import com.dongdao.meetingmanager.info.Weatherpic;
import com.dongdao.meetingmanager.service.MyService;
import com.dongdao.meetingmanager.view.MtTextView;
import com.lidroid.xutils.BitmapUtils;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by Administrator on 2016/9/18.
 */
public class MeetingActivity extends FragmentActivity implements MyCallBackHandle {
    @BindView(R.id.txtclock1)
    TextClock txtclock1;
    @BindView(R.id.txtclock)
    TextClock txtclock;
    @BindView(R.id.tips)
    MtTextView tips;
    @BindView(R.id.texttq)
    TextView tqtxt;
    @BindView(R.id.weather)
    TextView textview;
    @BindView(R.id.nowroom)
    TextView nowroom;
    @BindView(R.id.nowtheme)
    TextView nowtheme;
    @BindView(R.id.nowtime)
    TextView nowtime;
    @BindView(R.id.nowuser)
    TextView nowuser;
    @BindView(R.id.imageViewtq)
    ImageView tqimg;
    @BindView(R.id.meetings)
    ListView mListView;
    @BindView(R.id.nowbg)
    LinearLayout mLayout;
    @BindView(R.id.myviewflipper)
    ViewFlipper mFlipper;
    private List<Meetinginfo> nowMeetinginfos = new ArrayList<Meetinginfo>();
    private List<Pic> mPics = new ArrayList<>();
    private Meetinginfo mMeetinginfo;
    private MeetingAdapter mAdapter;
    private MyStringCallBack mBack;
    private MsgReceiver msgReceiver;
    private BitmapUtils mUtils;
    private Weatherpic mWeather;
    private String temp;
    private String tq;

    class MsgReceiver extends BroadcastReceiver {

        public MsgReceiver() {

        }

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            if (action.equals("com.example.communication.RECEIVER")) {
                nowMeetinginfos = (List<Meetinginfo>) intent.getSerializableExtra("nowmeeting");
                if (nowMeetinginfos.size() <= 0) {
                    nowroom.setText("当前室会议");
                    nowtheme.setText("空闲");
                    nowtime.setText("空闲");
                    nowuser.setText("空闲");
                } else {
                    mMeetinginfo = nowMeetinginfos.get(0);
                    nowroom.setText(mMeetinginfo.getMeetingroom() + "会议室会议");
                    nowtheme.setText(mMeetinginfo.getMeetingtheme());
                    nowtime.setText(mMeetinginfo.getBegintime().substring(0, 5) + "--" + mMeetinginfo.getEndtime().substring(0, 5));
                    nowuser.setText(mMeetinginfo.getMeetinguser());
                }
                mAdapter = new MeetingAdapter((List<Meetinginfo>) intent.getSerializableExtra("meetings"), MeetingActivity.this);
                mListView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
            if (action.equals("before")) {
                Toast.makeText(MeetingActivity.this, "正在更新数据", Toast.LENGTH_SHORT).show();
            }
            if (action.equals("weather")) {
                mWeather = (Weatherpic) intent.getSerializableExtra("cond");
                tqtxt.setText(mWeather.getTxt());
                temp = intent.getStringExtra("tmp");
                textview.setText(temp + "℃");
                getTqpic(tqimg, mWeather.getCode());
            }
            if (action.equals("error")) {
                Toast.makeText(MeetingActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_meeting_list);
        ButterKnife.bind(this);
        mUtils = new BitmapUtils(this);
        initView();
        initData();
    }

    //初始化数据 通过网络请求
    private void initData() {
        msgReceiver = new MsgReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.communication.RECEIVER");
        intentFilter.addAction("before");
        intentFilter.addAction("after");
        intentFilter.addAction("weather");
        intentFilter.addAction("error");
        registerReceiver(msgReceiver, intentFilter);
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
        getPic();
    }

    private void initAdapter() {

    }

    private void initListener() {

    }

    //初始化控件
    private void initView() {
        nowroom.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);//下划线
        mLayout.setBackgroundResource(R.drawable.rightmeeting);
    }

    @Override
    public void OnError(Call call, int i, Exception e) {

    }

    @Override
    public void onResponse(Object s, int i) {
        switch (i) {
            case 1:
                break;
            case 2:
                parse(s.toString(), 2);
                break;
            case 3:
                break;
        }
    }

    @Override
    public void onBefore(Request request, int id) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(msgReceiver);
        mUtils = null;
    }

    //JSON 解析
    private void parse(String s, int id) {
        switch (id) {
            case 2:
                JSONObject object = JSON.parseObject(s);
                JSONObject result = object.getJSONObject("result");
                JSONArray picList = result.getJSONArray("picList");
                mPics = JSON.parseArray(picList.toString(), Pic.class);
                for (int i = 0; i < mPics.size(); i++) {
                    String url = mPics.get(i).getPicpath();
                    ImageView view = new ImageView(this);
                    view.setAdjustViewBounds(true);
                    mUtils.display(view, url);
                    mFlipper.addView(view);
                }
                mFlipper.startFlipping();
                break;
        }

    }

    //获取图片
    private void getPic() {
        mBack = new MyStringCallBack(this);
        HttpUtils.post("http://192.168.1.76:8702/jinfeng/cfpic/allpic.do", 2, null, mBack);
    }

    private void getTqpic(ImageView tqimg, String code) {
        switch (code) {
            case "100":
                tqimg.setImageResource(R.mipmap.t100);
                break;
            case "101":
                tqimg.setImageResource(R.mipmap.t101);
                break;
            case "102":
                tqimg.setImageResource(R.mipmap.t102);
                break;
            case "103":
                tqimg.setImageResource(R.mipmap.t103);
                break;
            case "104":
                tqimg.setImageResource(R.mipmap.t104);
                break;
            case "200":
                tqimg.setImageResource(R.mipmap.t200);
                break;
            case "201":
                tqimg.setImageResource(R.mipmap.t201);
                break;
            case "202":
                tqimg.setImageResource(R.mipmap.t202);
                break;
            case "203":
                tqimg.setImageResource(R.mipmap.t203);
                break;
            case "204":
                tqimg.setImageResource(R.mipmap.t204);
                break;
            case "205":
                tqimg.setImageResource(R.mipmap.t205);
                break;
            case "206":
                tqimg.setImageResource(R.mipmap.t206);
                break;
            case "207":
                tqimg.setImageResource(R.mipmap.t207);
                break;
            case "208":
                tqimg.setImageResource(R.mipmap.t208);
                break;
            case "209":
                tqimg.setImageResource(R.mipmap.t209);
                break;
            case "210":
                tqimg.setImageResource(R.mipmap.t210);
                break;
            case "211":
                tqimg.setImageResource(R.mipmap.t211);
                break;
            case "212":
                tqimg.setImageResource(R.mipmap.t212);
                break;
            case "213":
                tqimg.setImageResource(R.mipmap.t213);
                break;
            case "300":
                tqimg.setImageResource(R.mipmap.t300);
                break;
            case "301":
                tqimg.setImageResource(R.mipmap.t301);
                break;
            case "302":
                tqimg.setImageResource(R.mipmap.t302);
                break;
            case "303":
                tqimg.setImageResource(R.mipmap.t303);
                break;
            case "304":
                tqimg.setImageResource(R.mipmap.t304);
                break;
            case "305":
                tqimg.setImageResource(R.mipmap.t305);
                break;
            case "306":
                tqimg.setImageResource(R.mipmap.t306);
                break;
            case "307":
                tqimg.setImageResource(R.mipmap.t307);
                break;
            case "308":
                tqimg.setImageResource(R.mipmap.t308);
                break;
            case "309":
                tqimg.setImageResource(R.mipmap.t309);
                break;
            case "310":
                tqimg.setImageResource(R.mipmap.t310);
                break;
            case "311":
                tqimg.setImageResource(R.mipmap.t311);
                break;
            case "312":
                tqimg.setImageResource(R.mipmap.t312);
                break;
            case "313":
                tqimg.setImageResource(R.mipmap.t313);
                break;
            case "400":
                tqimg.setImageResource(R.mipmap.t400);
                break;
            case "401":
                tqimg.setImageResource(R.mipmap.t401);
                break;
            case "402":
                tqimg.setImageResource(R.mipmap.t402);
                break;
            case "403":
                tqimg.setImageResource(R.mipmap.t403);
                break;
            case "404":
                tqimg.setImageResource(R.mipmap.t404);
                break;
            case "405":
                tqimg.setImageResource(R.mipmap.t405);
                break;
            case "406":
                tqimg.setImageResource(R.mipmap.t406);
                break;
            case "407":
                tqimg.setImageResource(R.mipmap.t407);
                break;
            case "500":
                tqimg.setImageResource(R.mipmap.t500);
                break;
            case "501":
                tqimg.setImageResource(R.mipmap.t501);
                break;
            case "502":
                tqimg.setImageResource(R.mipmap.t502);
                break;
            case "503":
                tqimg.setImageResource(R.mipmap.t503);
                break;
            case "504":
                tqimg.setImageResource(R.mipmap.t504);
                break;
            case "507":
                tqimg.setImageResource(R.mipmap.t507);
                break;
            case "508":
                tqimg.setImageResource(R.mipmap.t508);
                break;
            case "901":
                tqimg.setImageResource(R.mipmap.t901);
                break;
            case "999":
                tqimg.setImageResource(R.mipmap.t999);
                break;
        }
    }

}
