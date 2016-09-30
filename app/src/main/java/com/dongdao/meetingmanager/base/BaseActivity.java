package com.dongdao.meetingmanager.base;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.dongdao.meetingmanager.basereceiver.NetworkReceiver;

/**
 * Created by Administrator on 2016/9/19.
 * 所有的Activity都继承此BaseActivity
 */
public abstract class BaseActivity extends Activity {

    private NetworkReceiver mReceiver;//监听网络状态的广播
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册广播
        mReceiver=new NetworkReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver,filter);
        //Activity的一些初始化
        setView();
        initData();
        initListener();
    }
    //加载布局文件
    protected abstract void setView();
    //初始化数据
    protected abstract void initData();
    //初始化监听事件
    protected abstract void initListener();
    //查找子控件
    public View findView(int id){
        return findViewById(id);
    }

    //Activity的各个运行状态
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
    //销毁广播
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }
    //屏幕上的回退按钮处理
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }
}
