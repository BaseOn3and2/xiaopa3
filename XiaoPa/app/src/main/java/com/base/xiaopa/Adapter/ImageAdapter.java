package com.base.xiaopa.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.base.xiaopa.constant.ImgBean;
import com.xiaopa.android.R;

import java.util.ArrayList;

/**
 * Created by Ivan on 2017/10/28.
 */

public class ImageAdapter extends BaseAdapter {
    private ArrayList<ImgBean> list;
    private Context context;

    public ImageAdapter(Context context, ArrayList<ImgBean> list){
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
            view = View.inflate(context, R.layout.item_image_layout,null);
        }
        ImageView show = (ImageView) view.findViewById(R.id.iv_show);
        //获取position位置条目对应的list集合的群组数据
        ImgBean imgBean = list.get(position);
        show.setImageDrawable(imgBean.src);
        return view;
    }
}
