package com.dongdao.meetingmanager.http;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by Administrator on 2016/9/19.
 * 自定义回调处理接口
 */
public interface MyCallBackHandle {
    abstract void OnError(Call call, int i,Exception e);//错误情况
    abstract void onResponse(Object s,int i);//回调结果
    abstract void onBefore(Request request, int id);//网络请求前的准备
}
