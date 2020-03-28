package com.demo.notpadapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.notpadapp.R;
import com.demo.notpadapp.bean.ItemBean;

import java.util.List;

public class MainAdapter extends BaseAdapter {


    private List<ItemBean> itemBeans;
    private LayoutInflater layoutInflater;

    public MainAdapter(Context context, List<ItemBean> itemBeans) {
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
            convertView = layoutInflater.inflate(R.layout.item_main_view, parent, false);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.tv_name);
            holder.icon = (ImageView) convertView.findViewById(R.id.iv_icon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ItemBean provinceBean = itemBeans.get(position);
        if (provinceBean != null) {
            holder.text.setText(provinceBean.getName());
            holder.icon.setImageResource(provinceBean.getIcon());
        }
        return convertView;
    }

    class ViewHolder {
        TextView text;
        ImageView icon;
    }

}