package com.base.xiaopa.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.base.xiaopa.Adapter.ImageAdapter;
import com.base.xiaopa.constant.ImgBean;
import com.base.xiaopa.util.ImageUtils;
import com.xiaopa.android.R;

import java.util.ArrayList;

/**
 * Created by Ivan on 2017/10/28.
 */

public class ImgShowActivity extends Fragment {
    View v;
    ListView lv_show;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.event_imageshow, container, false);
        lv_show = (ListView)v.findViewById(R.id.lv_ImgShow);
        ArrayList<ImgBean> allImage = ImageUtils.getAllGroup(getContext());
        ImageAdapter imageAdapter = new ImageAdapter(getContext(),allImage);
        lv_show.setAdapter(imageAdapter);
        return v;
    }
}
