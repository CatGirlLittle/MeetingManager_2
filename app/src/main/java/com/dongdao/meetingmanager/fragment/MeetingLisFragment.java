package com.dongdao.meetingmanager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dongdao.meetingmanager.R;
import com.dongdao.meetingmanager.adapter.MeetingAdapter;
import com.dongdao.meetingmanager.http.HttpUtils;
import com.dongdao.meetingmanager.http.MyCallBackHandle;
import com.dongdao.meetingmanager.http.MyStringCallBack;
import com.dongdao.meetingmanager.info.Meetinginfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by Administrator on 2016/9/18.
 */
public class MeetingLisFragment extends Fragment implements MyCallBackHandle {
    private ListView mListView;
    private MeetingAdapter mAdapter;
    private List<Meetinginfo> mMeetinginfos;
    private MyStringCallBack mBack;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View conview=  inflater.inflate(R.layout.meeting_list_fragment,container,false);
        mListView= (ListView) conview.findViewById(R.id.meetinglist);
        initData();
        initAdapter();
        return conview;
    }

    private void initAdapter() {
        mAdapter=new MeetingAdapter(mMeetinginfos,getActivity());
        mListView.setAdapter(mAdapter);
    }

    private void initData() {
         Meetinginfo meetinginfo=null;
        mMeetinginfos=new ArrayList<Meetinginfo>();
        for (int i=0;i<=10;i++){
            if(i%2==1){
                meetinginfo=new Meetinginfo("100","南航11111111111111111111111111111111111111","2016-09-18","移动互联网","09:00","11:00",1);
                mMeetinginfos.add(meetinginfo);
            }else{
                meetinginfo=new Meetinginfo("101","- -","- -","- -","","",0);
                mMeetinginfos.add(meetinginfo);
            }
        }
        getHttp();
    }

    private void getHttp(){
        Map map=new HashMap();
        map.put("meetingroom","302");
        map.put("page","1");
        mBack=new MyStringCallBack(this);
        HttpUtils.post("http:192.168.6.97:8080/myMeet/meetings.do",1,map,mBack);
    }

    @Override
    public void OnError(Call call, int i, Exception e) {

    }

    @Override
    public void onResponse(Object s, int i) {
        if(s instanceof String){
            JSONObject object= (JSONObject) JSON.parse((String) s);
            JSONArray meetinginfos_1=object.getJSONArray("");
            JSONArray meetinginfos_2=object.getJSONArray("");
            mMeetinginfos=JSON.parseArray(meetinginfos_1.toString(),Meetinginfo.class);
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

}
