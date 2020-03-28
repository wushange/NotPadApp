package com.demo.notpadapp;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.blankj.utilcode.util.ActivityUtils;
import com.demo.notpadapp.activity.AccountBookActivity;
import com.demo.notpadapp.activity.AccountViewActivity;
import com.demo.notpadapp.activity.DataListActivity;
import com.demo.notpadapp.activity.IncomeActivity;
import com.demo.notpadapp.activity.IncomeListActivity;
import com.demo.notpadapp.activity.LoginActivity;
import com.demo.notpadapp.activity.MarkActivity;
import com.demo.notpadapp.activity.PayActivity;
import com.demo.notpadapp.activity.PayListActivity;
import com.demo.notpadapp.activity.SettingActivity;
import com.demo.notpadapp.adapter.MainAdapter;
import com.demo.notpadapp.base.BaseActivity;
import com.demo.notpadapp.bean.ItemBean;
import com.demo.notpadapp.util.UserUtil;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity {
    private GridView mGridview;
    private MainAdapter mMadapter;
    private String[] itemNames = new String[]{"新增支出", "新增收入",
            "我的支出", "我的收入", "数据管理",
            "系统设置", "新增便签", "今日账单",
            "本月账单", "本年账单", "退出"
    };
    private int[] icons = new int[]{R.mipmap.onr, R.mipmap.two,
            R.mipmap.three, R.mipmap.four, R.mipmap.five,
            R.mipmap.six, R.mipmap.seven, R.mipmap.eight,
            R.mipmap.nine, R.mipmap.four, R.mipmap.five};

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {
        mGridview = (GridView) findViewById(R.id.gridview);
        List<ItemBean> itemBeans = new ArrayList<>();
        for (int i = 0; i < itemNames.length; i++) {
            ItemBean provinceBean = new ItemBean();
            provinceBean.setName(itemNames[i]);
            provinceBean.setIcon(icons[i]);
            itemBeans.add(provinceBean);
        }
        mMadapter = new MainAdapter(this, itemBeans);
        mGridview.setAdapter(mMadapter);
        mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        ActivityUtils.startActivity(PayActivity.class);
                        break;
                    case 1:
                        ActivityUtils.startActivity(IncomeActivity.class);
                        break;
                    case 2:
                        ActivityUtils.startActivity(PayListActivity.class);
                        break;
                    case 3:
                        ActivityUtils.startActivity(IncomeListActivity.class);
                        break;
                    case 4:
                        ActivityUtils.startActivity(DataListActivity.class);
                        break;
                    case 5:
                        ActivityUtils.startActivity(SettingActivity.class);
                        break;
                    case 6:
                        ActivityUtils.startActivity(MarkActivity.class);
                        break;
                    case 7:
                        AccountViewActivity.callMe(getContext(),0);
                        break;
                    case 8:
                        AccountViewActivity.callMe(getContext(),1);
                        break;
                    case 9:
                        AccountViewActivity.callMe(getContext(),2);
                        break;
                    case 10:
                        ActivityUtils.startActivity(LoginActivity.class);
                        UserUtil.logout();
                        finish();
                        break;
                    default:

                }
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

    }
}
