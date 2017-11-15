package com.base.xiaopa.util;

import android.content.Context;

import com.base.xiaopa.constant.ImgBean;
import com.xiaopa.android.R;

import java.util.ArrayList;

/**
 * Created by Ivan on 2017/10/28.
 */

public class ImageUtils {
    public static ArrayList<ImgBean> getAllGroup(Context context){
        ArrayList<ImgBean> arrayList = new ArrayList<ImgBean> ();

        ImgBean imgBean = new ImgBean();
        imgBean.src = context.getResources().getDrawable(R.drawable.t6);
        arrayList.add(imgBean);

        ImgBean imgBean1 = new ImgBean();
        imgBean1.src = context.getResources().getDrawable(R.drawable.t6);
        arrayList.add(imgBean1);

        ImgBean imgBean2 = new ImgBean();
        imgBean2.src = context.getResources().getDrawable(R.drawable.t6);
        arrayList.add(imgBean2);

        ImgBean imgBean3 = new ImgBean();
        imgBean3.src = context.getResources().getDrawable(R.drawable.t6);
        arrayList.add(imgBean3);

        ImgBean imgBean4 = new ImgBean();
        imgBean4.src = context.getResources().getDrawable(R.drawable.t6);
        arrayList.add(imgBean4);

        ImgBean imgBean5 = new ImgBean();
        imgBean5.src = context.getResources().getDrawable(R.drawable.t6);
        arrayList.add(imgBean5);
        return arrayList;

    }
}
