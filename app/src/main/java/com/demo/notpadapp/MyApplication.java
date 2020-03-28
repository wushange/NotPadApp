package com.demo.notpadapp;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;

import org.litepal.LitePal;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        LitePal.initialize(this);
        LitePal.getDatabase();

    }
}
