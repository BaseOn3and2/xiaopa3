package com.base.xiaopa.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.base.xiaopa.base.BaseFragment;
import com.base.xiaopa.model.UserModel;
import com.xiaopa.android.R;

import butterknife.OnClick;
import cn.bmob.newim.BmobIM;

/**
 * Created by Ivan on 2017/10/10.
 */

public class MeActivity extends Fragment {
    View v;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.activity_me,container,false);

        return v;
    }

}

