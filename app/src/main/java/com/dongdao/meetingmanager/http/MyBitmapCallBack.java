package com.dongdao.meetingmanager.http;

import android.graphics.Bitmap;

import com.zhy.http.okhttp.callback.BitmapCallback;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/9/19.
 * 自定义图片回调
 */
public class MyBitmapCallBack extends BitmapCallback {

    private MyCallBackHandle mHandle;

    public MyBitmapCallBack(MyCallBackHandle handle) {
        mHandle = handle;
    }

    @Override
    public void onError(Call call, Exception e, int i) {
            mHandle.OnError(call,i,e);
    }

    @Override
    public void onResponse(Bitmap bitmap, int i) {
            mHandle.onResponse(bitmap,i);
    }
}
