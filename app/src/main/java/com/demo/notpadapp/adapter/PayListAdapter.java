package com.demo.notpadapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.demo.notpadapp.R;
import com.demo.notpadapp.activity.PayActivity;
import com.demo.notpadapp.bean.Income;
import com.demo.notpadapp.bean.Pay;

import java.util.List;

public class PayListAdapter extends BaseAdapter {

    private Context context;
    private List<Pay> itemBeans;
    private LayoutInflater layoutInflater;

    public PayListAdapter(Context context, List<Pay> itemBeans) {
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
            convertView = layoutInflater.inflate(R.layout.item_pay_view, parent, false);
            holder = new ViewHolder();
            holder.tvMoney = (TextView) convertView.findViewById(R.id.tv_pay_money);
            holder.tvTime = (TextView) convertView.findViewById(R.id.tv_pay_time);
            holder.tvType = (TextView) convertView.findViewById(R.id.tv_pay_type);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Pay pay = itemBeans.get(position);
        if (pay != null) {
            holder.tvType.setText(pay.getType());
            holder.tvMoney.setText("" + pay.getMoney()+" 元");
            holder.tvTime.setText(TimeUtils.millis2String(pay.getTime(), "yyyy-MM-dd"));
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PayActivity.callMe(context, pay);
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView tvType, tvMoney, tvTime;
    }

}