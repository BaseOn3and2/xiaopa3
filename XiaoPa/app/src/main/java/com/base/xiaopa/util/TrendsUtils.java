package com.base.xiaopa.util;

import android.content.Context;

import com.base.xiaopa.constant.TrendsBean;
import com.xiaopa.android.R;

import java.util.ArrayList;

/**
 * Created by Ivan on 2017/11/13.
 */

public class TrendsUtils {
    public static ArrayList<TrendsBean> getAllCompete(Context context) {
        ArrayList<TrendsBean> arrayList = new ArrayList<TrendsBean>();

        TrendsBean trendsBean0 = new TrendsBean();
        trendsBean0.img = context.getResources().getDrawable(R.drawable.goodvoice);
        trendsBean0.competeName="校园好声音";
        trendsBean0.competeType="个人比赛";
        arrayList.add(trendsBean0);

        TrendsBean trendsBean1 = new TrendsBean();
        trendsBean1.img = context.getResources().getDrawable(R.drawable.marketing);
        trendsBean1.competeName="营销策划大赛";
        trendsBean1.competeType="团队比赛";
        arrayList.add(trendsBean1);

        TrendsBean trendsBean2 = new TrendsBean();
        trendsBean2.img = context.getResources().getDrawable(R.drawable.lanqiaobei);
        trendsBean2.competeName="蓝桥杯大赛";
        trendsBean2.competeType="个人比赛";
        arrayList.add(trendsBean2);

        TrendsBean trendsBean3 = new TrendsBean();
        trendsBean3.img = context.getResources().getDrawable(R.drawable.mess_business);
        trendsBean3.competeName="软件设计大赛";
        trendsBean3.competeType="团队比赛";
        arrayList.add(trendsBean3);

        return arrayList;
    }
}
