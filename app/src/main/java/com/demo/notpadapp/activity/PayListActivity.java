package com.demo.notpadapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.demo.notpadapp.R;
import com.demo.notpadapp.adapter.PayListAdapter;
import com.demo.notpadapp.base.BaseActivity;
import com.demo.notpadapp.bean.Pay;
import com.demo.notpadapp.util.UserUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 支出列表
 */
public class PayListActivity extends BaseActivity {
    List<Pay> payList = new ArrayList<>();
    private ListView mListview;
    private PayListAdapter payListAdapter;

    @Override
    public int bindLayout() {
        return R.layout.activity_paylist;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {
        mListview = (ListView) findViewById(R.id.listview);
        setList();
    }

    private void setList() {
        payList = UserUtil.getPayList();
        mListview.setAdapter(payListAdapter = new PayListAdapter(this, payList));
    }

    @Override
    protected void onResume() {
        super.onResume();
        setList();
    }

    @Override
    public void doBusiness(Context mContext) {

    }
}
