package com.demo.notpadapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.demo.notpadapp.R;
import com.demo.notpadapp.adapter.IncomeListAdapter;
import com.demo.notpadapp.adapter.MarkListAdapter;
import com.demo.notpadapp.adapter.PayListAdapter;
import com.demo.notpadapp.base.BaseActivity;
import com.demo.notpadapp.util.UserUtil;

/**
 * 数据统计
 */
public class DataListActivity extends BaseActivity {
    private Button mBtnPayList;
    private Button mBtnIncomeList;
    private Button mBtnMarkList;
    private Button mBtnNoteSum;
    private ListView mDataListview;
    private PayListAdapter payListAdapter;
    private IncomeListAdapter incomeListAdapter;
    private MarkListAdapter markListAdapter;
    private int postion = 0;
    private TextView mTvTip;


    @Override
    public int bindLayout() {
        return R.layout.activity_datalist;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {
        mTvTip = (TextView) findViewById(R.id.tv_tip);
        mBtnPayList = (Button) findViewById(R.id.btn_pay_list);
        mBtnIncomeList = (Button) findViewById(R.id.btn_income_list);
        mBtnMarkList = (Button) findViewById(R.id.btn_mark_list);
        mBtnNoteSum = (Button) findViewById(R.id.btn_note_sum);
        mDataListview = (ListView) findViewById(R.id.data_listview);
        setPayData();
        mBtnPayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postion = 0;
                mTvTip.setText("支出信息：");
                setPayData();
            }
        });
        mBtnIncomeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postion = 1;
                mTvTip.setText("收入信息：");
                setIncomeData();
            }
        });
        mBtnMarkList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postion = 2;
                mTvTip.setText("便签信息：");
                setMarkData();
            }
        });
        mBtnNoteSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postion = 3;
                mTvTip.setText("账单统计：");
                ActivityUtils.startActivity(AccountBookActivity.class);

            }
        });

    }

    private void setMarkData() {
        mDataListview.setAdapter(markListAdapter = new MarkListAdapter(getContext(), UserUtil.getMarkList()));
    }

    private void setIncomeData() {
        mDataListview.setAdapter(incomeListAdapter = new IncomeListAdapter(getContext(), UserUtil.getIncomeList()));
    }

    private void setPayData() {
        mDataListview.setAdapter(payListAdapter = new PayListAdapter(getContext(), UserUtil.getPayList()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        switch (postion) {
            case 0:
                setPayData();
                break;
            case 1:
                setIncomeData();
                break;
            case 2:
                setMarkData();
                break;
            case 3:
                break;
            default:
        }
    }

    @Override
    public void doBusiness(Context mContext) {

    }
}
