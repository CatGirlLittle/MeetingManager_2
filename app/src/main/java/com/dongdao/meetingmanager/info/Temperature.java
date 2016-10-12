package com.dongdao.meetingmanager.info;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/12.
 */

public class Temperature implements Serializable{
    Weatherpic mWeatherpic;
    /*"tmp": "17",*/
    String tep;

    public Temperature() {
    }

    public Temperature(Weatherpic weatherpic, String tep) {
        mWeatherpic = weatherpic;
        this.tep = tep;
    }

    public Weatherpic getWeatherpic() {
        return mWeatherpic;
    }

    public void setWeatherpic(Weatherpic weatherpic) {
        mWeatherpic = weatherpic;
    }

    public String getTep() {
        return tep;
    }

    public void setTep(String tep) {
        this.tep = tep;
    }
}
