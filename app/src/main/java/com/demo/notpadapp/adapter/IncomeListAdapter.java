package com.demo.notpadapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.demo.notpadapp.R;
import com.demo.notpadapp.activity.IncomeActivity;
import com.demo.notpadapp.bean.Income;
import com.demo.notpadapp.bean.ItemBean;

import java.util.List;

public class IncomeListAdapter extends BaseAdapter {

    private Context context;
    private List<Income> itemBeans;
    private LayoutInflater layoutInflater;

    public IncomeListAdapter(Context context, List<Income> itemBeans) {
        this.context = context;
        this.itemBeans = itemBeans;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return itemBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_income_view, parent, false);
            holder = new ViewHolder();
            holder.tvMoney = (TextView) convertView.findViewById(R.id.tv_income_money);
            holder.tvTime = (TextView) convertView.findViewById(R.id.tv_income_time);
            holder.tvType = (TextView) convertView.findViewById(R.id.tv_income_type);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Income income = itemBeans.get(position);
        if (income != null) {
            holder.tvType.setText(income.getType());
            holder.tvMoney.setText("" + income.getMoney()+" 元");
            holder.tvTime.setText(TimeUtils.millis2String(income.getTime(), "yyyy-MM-dd"));
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IncomeActivity.callMe(context, income);
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView tvType, tvMoney, tvTime;
    }

}