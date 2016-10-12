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
    /*tmp	当前温度(摄氏度)
    fl	体感温度
    wind	风力状况
    spd	风速(Kmph)
    sc	风力等级
    deg	风向(角度)
    dir	风向(方向)
    cond	天气状况
    code	天气代码 图片
    txt	天气描述
    pcpn	降雨量(mm)
    hum	湿度(%)
    pres	气压
    vis	能见度(km)*/
   /* "now": {
        "cond": {
            "code": "104",
                    "txt": "阴"
        },
        "fl": "11",
        "hum": "65",
        "pcpn": "0",
                "tmp": "17",
                "wind": {
            "dir": "西南风",
                    "sc": "4-5",
                    "spd": "23"
        }
    }*/
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
