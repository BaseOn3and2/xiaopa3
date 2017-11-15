package com.base.xiaopa.util;

import android.content.Context;

import com.base.xiaopa.constant.FellowshipBean;
import com.xiaopa.android.R;

import java.util.ArrayList;

/**
 * Created by Ivan on 2017/11/14.
 */

public class FellowshipUtils {
    public static ArrayList<FellowshipBean> getAllFellowship(Context context) {
        ArrayList<FellowshipBean> arrayList = new ArrayList<FellowshipBean>();

        FellowshipBean fellowshipBean0 = new FellowshipBean();
        fellowshipBean0.myBackground = context.getResources().getDrawable(R.drawable.assn_bg);
        fellowshipBean0.summary= "160802班与160104班 联谊";
        arrayList.add(fellowshipBean0);

        FellowshipBean fellowshipBean1 = new FellowshipBean();
        fellowshipBean1.myBackground = context.getResources().getDrawable(R.drawable.assn_bg);
        fellowshipBean1.summary= "160802班与160104班 联谊";
        arrayList.add(fellowshipBean1);

        FellowshipBean fellowshipBean2 = new FellowshipBean();
        fellowshipBean2.myBackground = context.getResources().getDrawable(R.drawable.assn_bg);
        fellowshipBean2.summary= "160802班与160104班 联谊";
        arrayList.add(fellowshipBean2);

        return  arrayList;
    }
}
