package com.dongdao.meetingmanager.info;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/10.
 */

public class Weather implements Serializable {
    /*"fengxiang": "无持续风向",
            "fengli": "微风级",
            "high": "高温 19℃",
            "type": "多云",
            "low": "低温 10℃",
            "date": "10日星期一"*/
    String fengxiang;
    String fengli;
    String high;
    String type;
    String low;
    String date;

    public Weather() {
    }

    public Weather(String fengxiang, String fengli, String high, String type, String low, String date) {
        this.fengxiang = fengxiang;
        this.fengli = fengli;
        this.high = high;
        this.type = type;
        this.low = low;
        this.date = date;
    }

    public String getFengxiang() {
        return fengxiang;
    }

    public void setFengxiang(String fengxiang) {
        this.fengxiang = fengxiang;
    }

    public String getFengli() {
        return fengli;
    }

    public void setFengli(String fengli) {
        this.fengli = fengli;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "fengxiang='" + fengxiang + '\'' +
                ", fengli='" + fengli + '\'' +
                ", high='" + high + '\'' +
                ", type='" + type + '\'' +
                ", low='" + low + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
