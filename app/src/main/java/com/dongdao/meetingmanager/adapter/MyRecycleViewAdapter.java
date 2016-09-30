package com.dongdao.meetingmanager.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dongdao.meetingmanager.R;
import com.dongdao.meetingmanager.info.Meetinginfo;
import com.dongdao.meetingmanager.view.MtTextView;

import java.util.List;

/**
 * Created by Administrator on 2016/9/21.
 */
public class MyRecycleViewAdapter extends RecyclerView.Adapter {
    //事件接口
    //List<>
    private List<Meetinginfo> mMeetinginfos;
    //构造参数

    public MyRecycleViewAdapter(List<Meetinginfo> meetinginfos) {
        this.mMeetinginfos = meetinginfos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.meetingitemlayout,null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new MyRecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyRecycleViewHolder myRecycleViewHolder= (MyRecycleViewHolder) holder;
        myRecycleViewHolder.position=position;
        //设置内容 设置实体
        Meetinginfo meetinginfo=mMeetinginfos.get(position);

    }

    @Override
    public int getItemCount() {
        return mMeetinginfos.size();

    }
    class MyRecycleViewHolder extends RecyclerView.ViewHolder{
        //设置事件
        TextView room,date,time;
        MtTextView name,people;
        LinearLayout bg;
        public int position;

        public MyRecycleViewHolder(View itemView) {
            super(itemView);
            //findviewByid
            room= (TextView) itemView.findViewById(R.id.room);
            date= (TextView) itemView.findViewById(R.id.date);
            time= (TextView) itemView.findViewById(R.id.time);
            name= (MtTextView) itemView.findViewById(R.id.meetingname);
            people= (MtTextView) itemView.findViewById(R.id.people);
            bg= (LinearLayout) itemView.findViewById(R.id.meetingbg);
            //设置监听事件
        }
    }
}
