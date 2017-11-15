package com.base.xiaopa.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.xiaopa.constant.TrendsBean;
import com.xiaopa.android.R;

import java.util.ArrayList;

/**
 * Created by Ivan on 2017/11/13.
 */

public class TrendsAdapter extends BaseAdapter {
    private ArrayList<TrendsBean> list;
    private Context context;

    public TrendsAdapter(Context context,ArrayList<TrendsBean> list){
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
            view = View.inflate(context, R.layout.item_competition_layout,null);
        }
        //获取View上的子控件
        ImageView item_compete_img =(ImageView)view.findViewById(R.id.item_compete_img);
        TextView item_compete_name = (TextView)view.findViewById(R.id.item_compete_name);
        TextView item_compete_type = (TextView)view.findViewById(R.id.item_compete_type);
        //获取position位置条目对应的list集合的群组数据
        TrendsBean trendsBean = list.get(position);
        item_compete_img.setImageDrawable(trendsBean.img);
        item_compete_name.setText(trendsBean.competeName);
        item_compete_type.setText(trendsBean.competeType);

        return view;
    }
}
