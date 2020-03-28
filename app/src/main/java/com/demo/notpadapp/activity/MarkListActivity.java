package com.demo.notpadapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.demo.notpadapp.R;
import com.demo.notpadapp.adapter.MarkListAdapter;
import com.demo.notpadapp.base.BaseActivity;
import com.demo.notpadapp.bean.Mark;
import com.demo.notpadapp.bean.User;
import com.demo.notpadapp.util.UserUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 便签列表
 */
public class MarkListActivity extends BaseActivity {
    private ListView mListview;

    private List<Mark> markList = new ArrayList<>();

    @Override
    public int bindLayout() {
        return R.layout.activity_marklistt;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {
        mListview = (ListView) findViewById(R.id.listview);
        mListview.setAdapter(new MarkListAdapter(this, markList = UserUtil.getMarkList()));
        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MarkActivity.callMe(getContext(), markList.get(i));
            }
        });
    }

    @Override
    public void doBusiness(Context mContext) {

    }
}
