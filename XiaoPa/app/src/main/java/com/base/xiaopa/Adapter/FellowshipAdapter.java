package com.base.xiaopa.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.xiaopa.constant.FellowshipBean;
import com.xiaopa.android.R;

import java.util.ArrayList;

/**
 * Created by Ivan on 2017/11/14.
 */

public class FellowshipAdapter extends BaseAdapter {
    private ArrayList<FellowshipBean> list;
    private Context context;

    public FellowshipAdapter(Context context,ArrayList<FellowshipBean> list){
        this.list = list;
        this.context = context;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        //复用convertView优化listView,创建一个view作为getview的返回值用来显示一个item
        if(convertView != null){
            view  = convertView;
        }else{
            view = View.inflate(context, R.layout.item_group_layout,null);
        }
        //获取View上的子控件
        ImageView item_fellowship_background =(ImageView)view.findViewById(R.id.item_fellowship_background);
        TextView item_fellowship_summary = (TextView)view.findViewById(R.id.item_fellowship_summary);
        //获取position位置条目对应的list集合的群组数据
        FellowshipBean fellowshipBean = list.get(position);
        item_fellowship_background.setImageDrawable(fellowshipBean.myBackground);
        item_fellowship_summary.setText(fellowshipBean.summary);
        return view;
    }
}
