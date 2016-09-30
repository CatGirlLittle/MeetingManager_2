package com.dongdao.meetingmanager.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.dongdao.meetingmanager.R;
import com.dongdao.meetingmanager.adapter.MyRecycleViewAdapter;
import com.dongdao.meetingmanager.base.BaseActivity;
import com.dongdao.meetingmanager.info.Meetinginfo;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/9/19.
 */
public class TestActivity extends BaseActivity implements OnClickListener{
    private TextView textView;
    private RecyclerView mView;
    private RecyclerView.LayoutManager mManager;
    private MyRecycleViewAdapter mAdapter;
    @Override
    protected void setView() {
        setContentView(R.layout.activity_meeting_list);
        textView= (TextView) findView(R.id.name);
       /* LinearLayoutManager 现行管理器，支持横向、纵向。
        GridLayoutManager 网格布局管理器
        StaggeredGridLayoutManager 瀑布就式布局管理器*/
        mView= (RecyclerView) findView(R.id.re);
        mManager=new LinearLayoutManager(this);
        //设置布局管理器
        mView.setLayoutManager(mManager);
       // mView.addItemDecoration();添加分割线
        //设置适配器
        mAdapter=new MyRecycleViewAdapter(new ArrayList<Meetinginfo>());
        mView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
    }

    @Override
    public void onClick(View v) {

    }
}
