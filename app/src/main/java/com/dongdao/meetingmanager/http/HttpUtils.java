package com.dongdao.meetingmanager.http;

import com.zhy.http.okhttp.OkHttpUtils;
import java.util.Map;


/**
 * Created by Administrator on 2016/9/18.
 * 网络请求工具类都为异步的
 */
//execute方法不传入callback即为同步的请求，返回Response。
public class HttpUtils {

    //ppost 方式
    public  static void post(String url, int id , Map <String,String> parms, MyStringCallBack myStringCallBack){
        OkHttpUtils.post()
                    .params(parms)
                    .url(url)
                    .id(id)
                    .build()
                    .execute(myStringCallBack);
    }
    //get方式
    public static void get(String url, int id , Map <String,String> parms, MyStringCallBack myStringCallBack){
        OkHttpUtils.get()
                   .params(parms)
                   .url(url)
                   .id(id)
                   .build()
                   .execute(myStringCallBack);

    }
}
