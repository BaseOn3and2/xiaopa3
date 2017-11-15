package com.base.xiaopa.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.base.xiaopa.Adapter.TrendsAdapter;
import com.base.xiaopa.constant.TrendsBean;
import com.base.xiaopa.util.TrendsUtils;
import com.xiaopa.android.R;

import java.util.ArrayList;

/**
 * Created by Ivan on 2017/11/14.
 */

public class FellowshipActivity extends Fragment {

    ListView lv_compete;
    Context mcontext;
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.activity_compete,container,false);
        mcontext = getContext();
        lv_compete = (ListView)v.findViewById(R.id.lv_compete);

        //获取群组数据用list封装
        ArrayList<TrendsBean> allCompete = TrendsUtils.getAllCompete(getContext());
        //创建Adapter设置给listView
        TrendsAdapter trendsAdapter = new TrendsAdapter(getContext(),allCompete);
        lv_compete.setAdapter(trendsAdapter);
        //设置点击事件
        lv_compete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //ListView的条目点击时会调用该方法
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(),SummaryActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }
}
