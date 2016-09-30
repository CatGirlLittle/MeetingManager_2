package com.dongdao.meetingmanager.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dongdao.meetingmanager.R;
import com.dongdao.meetingmanager.info.Meetinginfo;
import com.dongdao.meetingmanager.view.MtTextView;

import java.util.List;

/**
 * Created by Administrator on 2016/9/12.
 * 会议列表的适配器
 */
public class MeetingAdapter extends BaseAdapter {

    private List<Meetinginfo> mMeetinginfos;
    private Context mContext;

    public MeetingAdapter(List<Meetinginfo> meetinginfos, Context context) {
        mMeetinginfos = meetinginfos;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mMeetinginfos.size();
    }

    @Override
    public Object getItem(int position) {
        return mMeetinginfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.meetingitemlayout,null,false);
            holder=new ViewHolder();
            holder.room= (TextView) convertView.findViewById(R.id.room);
            holder.name= (MtTextView) convertView.findViewById(R.id.name);
            holder.date= (TextView) convertView.findViewById(R.id.date);
            holder.time= (TextView) convertView.findViewById(R.id.time);
            holder.people= (MtTextView) convertView.findViewById(R.id.people);
            holder.bg= (LinearLayout) convertView.findViewById(R.id.meetingbg);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
            isNull(holder,mMeetinginfos.get(position));

        return convertView;
    }

    class ViewHolder{
         TextView room,date,time;
         MtTextView name,people;
         LinearLayout bg;

    }
    private void isNull(ViewHolder holder,Meetinginfo meetinginfo){
        holder.room.setText(meetinginfo.getMeetingroom());
        if(meetinginfo.getMeetingtheme().length()<=0){
            holder.name.setText("空闲");
        }else{
            holder.name.setText(meetinginfo.getMeetingtheme());
        }
        if(meetinginfo.getMeetingtime().length()<=0){
            holder.date.setText("空闲");
        }else{
            holder.date.setText(meetinginfo.getMeetingtime());
        }
        if(meetinginfo.getBegintime().length()<=0&&meetinginfo.getEndtime().length()<=0){
            holder.time.setText("空闲");
        }else{
            holder.time.setText(meetinginfo.getBegintime().substring(0,5)+"--"+meetinginfo.getEndtime().substring(0,5));
        }
        if(meetinginfo.getMeetinguser().length()<=0){
            holder.people.setText("空闲");
        }else{
            holder.people.setText(meetinginfo.getMeetinguser());
        }

    };
}
