package com.dongdao.meetingmanager.info;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/12.
 */

public class Weatherpic implements Serializable{
    String code;
    String txt;
    /*"code": "104",
            "txt": "阴"*/

    public Weatherpic() {
    }

    public Weatherpic(String code, String txt) {
        this.code = code;
        this.txt = txt;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    @Override
    public String toString() {
        return "Weatherpic{" +
                "code='" + code + '\'' +
                ", txt='" + txt + '\'' +
                '}';
    }
}
