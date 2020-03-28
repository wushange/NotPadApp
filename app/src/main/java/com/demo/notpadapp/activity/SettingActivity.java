package com.demo.notpadapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SPUtils;
import com.demo.notpadapp.R;
import com.demo.notpadapp.base.BaseActivity;
import com.demo.notpadapp.util.UserUtil;

/**
 * 设置页面
 */
public class SettingActivity extends BaseActivity {
    private Button mBtnUpdateUser;
    private Button mBtnUpdateTheme;
    private Button mBtnLogout;


    @Override
    public int bindLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {

        mBtnUpdateUser = (Button) findViewById(R.id.btn_update_user);
        mBtnUpdateTheme = (Button) findViewById(R.id.btn_update_theme);
        mBtnLogout = (Button) findViewById(R.id.btn_logout);

        mBtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserUtil.logout();
                ActivityUtils.finishAllActivities();
                ActivityUtils.startActivity(LoginActivity.class);
            }
        });
        mBtnUpdateTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isNightMode = SPUtils.getInstance().getBoolean("IS_NIGHT_MODE");
                SPUtils.getInstance().put("IS_NIGHT_MODE", !isNightMode);
                AppCompatDelegate.setDefaultNightMode(!isNightMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
        mBtnUpdateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistActivity.callMe(getContext());
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

    }
}
