package com.demo.notpadapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.blankj.utilcode.util.TimeUtils;
import com.demo.notpadapp.R;
import com.demo.notpadapp.activity.MarkActivity;
import com.demo.notpadapp.bean.Mark;
import com.demo.notpadapp.bean.Pay;

import java.util.List;

public class MarkListAdapter extends BaseAdapter {

    private List<Mark> itemBeans;
    private LayoutInflater layoutInflater;
    private Context context;

    public MarkListAdapter(Context context, List<Mark> itemBeans) {
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
            convertView = layoutInflater.inflate(R.layout.item_mark_view,parent,false);
            holder = new ViewHolder();
            holder.tvText = (TextView) convertView.findViewById(R.id.tv_mark_text);
            holder.tvTime = (TextView) convertView.findViewById(R.id.tv_mark_time);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Mark mark = itemBeans.get(position);
        if (mark != null) {
            holder.tvText.setText(mark.getMark());
            holder.tvTime.setText(TimeUtils.millis2String(mark.getTime(),"yyyy-MM-dd"));
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MarkActivity.callMe(context,mark);
            }
        });
        return convertView;
    }
 
    class ViewHolder {
        TextView tvText,tvTime;
    }
 
}