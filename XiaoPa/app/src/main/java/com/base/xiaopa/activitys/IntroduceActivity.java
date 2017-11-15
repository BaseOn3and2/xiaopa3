package com.base.xiaopa.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaopa.android.R;

/**
 * Created by Ivan on 2017/10/28.
 */

public class IntroduceActivity extends Fragment {
    View v;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.event_introduce, container, false);
        return v;
    }
}
