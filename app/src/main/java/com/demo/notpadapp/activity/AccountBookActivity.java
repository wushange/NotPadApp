package com.demo.notpadapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.demo.notpadapp.R;
import com.demo.notpadapp.base.BaseActivity;
import com.demo.notpadapp.util.UserUtil;

/**
 * 账单统计
 */
public class AccountBookActivity extends BaseActivity {

    private TextView mTvDayPaySum;
    private TextView mTvDayIncomeSum;
    private TextView mTvMonthPaySum;
    private TextView mTvMonthIncomeSum;
    private TextView mTvYearPaySum;
    private TextView mTvYearIncomeSum;


    @Override
    public int bindLayout() {
        return R.layout.activity_account_book;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public void initView(View view) {
        mTvDayPaySum = (TextView) findViewById(R.id.tv_day_pay_sum);
        mTvDayIncomeSum = (TextView) findViewById(R.id.tv_day_income_sum);
        mTvMonthPaySum = (TextView) findViewById(R.id.tv_month_pay_sum);
        mTvMonthIncomeSum = (TextView) findViewById(R.id.tv_month_income_sum);
        mTvYearPaySum = (TextView) findViewById(R.id.tv_year_pay_sum);
        mTvYearIncomeSum = (TextView) findViewById(R.id.tv_year_income_sum);

        mTvDayPaySum.setText("" + UserUtil.getPaySumInDay() + " 元");
        mTvDayIncomeSum.setText("" + UserUtil.getIncomeSumInDay() + " 元");

        mTvMonthPaySum.setText("" + UserUtil.getPaySumInMonth() + " 元");
        mTvMonthIncomeSum.setText("" + UserUtil.getIncomeSumInMonth() + " 元");

        mTvYearPaySum.setText("" + UserUtil.getPaySumInYear() + " 元");
        mTvYearIncomeSum.setText("" + UserUtil.getIncomeSumInYear() + " 元");

    }

    @Override
    public void doBusiness(Context mContext) {

    }
}
