package com.dongdao.meetingmanager.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/9/19.
 * 所有的Fragment都继承此BaseFragment
 */

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=setView();
        initView();
        initData();
        initListener();
        return view;
    }
    protected  abstract View setView();
    protected abstract void initView();
    protected abstract void initData();
    protected abstract void initListener();
    public View findView(View view, int id){
        return view.findViewById(id);
    }
}
