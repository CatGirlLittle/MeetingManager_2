package com.dongdao.meetingmanager.basereceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/9/19.
 * 判断网络状态的广播
 */
public class NetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobNetInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);//手机自己的网络 了比如3G4G
        NetworkInfo wifiNetInfo=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);//WIFI连接状态
        if(!mobNetInfo.isConnected()&&!wifiNetInfo.isConnected()){
            Toast.makeText(context,"请检查网络设置",Toast.LENGTH_LONG).show();
        }else {
           Toast.makeText(context,"网络正常",Toast.LENGTH_LONG).show();
        }

    }
}
