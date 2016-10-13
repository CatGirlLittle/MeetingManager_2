package com.dongdao.meetingmanager.http;

import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by Administrator on 2016/9/18.
 * 自定义String类回调
 */
public class MyStringCallBack extends StringCallback {

    private MyCallBackHandle mHandle;

    public MyStringCallBack(MyCallBackHandle handle) {
        mHandle = handle;
    }

    @Override
    public void onError(Call call, Exception e, int i) {
        mHandle.OnError(call,i,e);
    }
    //异步
    @Override
    public void onResponse(String s, int i) {
        mHandle.onResponse(s,i);
    }

    @Override
    public void onBefore(Request request, int id) {
        mHandle.onBefore(request,id);
    }

}
