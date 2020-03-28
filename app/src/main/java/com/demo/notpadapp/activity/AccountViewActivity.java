package com.demo.notpadapp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.demo.notpadapp.R;
import com.demo.notpadapp.base.BaseActivity;
import com.demo.notpadapp.util.UserUtil;
import com.demo.notpadapp.widget.PieChartView;

import java.util.ArrayList;
import java.util.List;

/**
 * 饼状图页面
 */
public class AccountViewActivity extends BaseActivity {

    public static void callMe(Context context, int type) {
        Intent intent = new Intent(context, AccountViewActivity.class);
        intent.putExtra("TYPE", type);
        context.startActivity(intent);

    }

    int type = 0;

    @Override
    public int bindLayout() {
        return R.layout.activity_account_view;
    }

    @Override
    public void initParms(Bundle parms) {
        type = parms.getInt("TYPE");

    }

    @Override
    public void initView(View view) {
        PieChartView pieChartView = (PieChartView) findViewById(R.id.pie_chart_view);

        List<PieChartView.PieceDataHolder> pieceDataHolders = new ArrayList<>();
        switch (type) {
            case 0:
                pieceDataHolders.add(new PieChartView.PieceDataHolder(UserUtil.getPaySumInDay(), 0xFF77CCAA, "今日支出" + UserUtil.getPaySumInDay() + "元"));
                pieceDataHolders.add(new PieChartView.PieceDataHolder(UserUtil.getIncomeSumInDay(), Color.RED, "今日收入"+ UserUtil.getIncomeSumInDay() + "元"));
                break;
            case 1:
                pieceDataHolders.add(new PieChartView.PieceDataHolder(UserUtil.getPaySumInMonth(), 0xFF77CCAA, "本月支出"+ UserUtil.getPaySumInMonth() + "元"));
                pieceDataHolders.add(new PieChartView.PieceDataHolder(UserUtil.getIncomeSumInMonth(), Color.RED, "本月收入"+ UserUtil.getIncomeSumInMonth() + "元"));
                break;
            case 2:
                pieceDataHolders.add(new PieChartView.PieceDataHolder(UserUtil.getPaySumInYear(), 0xFF77CCAA, "本年支出"+ UserUtil.getPaySumInYear() + "元"));
                pieceDataHolders.add(new PieChartView.PieceDataHolder(UserUtil.getIncomeSumInYear(), Color.RED, "本年收入"+ UserUtil.getIncomeSumInYear() + "元"));
                break;
            default:
        }


        pieChartView.setData(pieceDataHolders);

    }

    @Override
    public void doBusiness(Context mContext) {

    }
}
