package com.demo.notpadapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.demo.notpadapp.R;
import com.demo.notpadapp.adapter.IncomeListAdapter;
import com.demo.notpadapp.base.BaseActivity;
import com.demo.notpadapp.bean.Income;
import com.demo.notpadapp.util.UserUtil;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

/**
 * 收入列表
 */
public class IncomeListActivity extends BaseActivity {
    List<Income> incomeList = new ArrayList<>();
    private ListView mListview;
    private IncomeListAdapter incomeListAdapter;



    @Override
    public int bindLayout() {
        return R.layout.activity_incomelist;
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
        incomeList = UserUtil.getIncomeList();
        mListview.setAdapter(incomeListAdapter = new IncomeListAdapter(this,incomeList));
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
