package com.base.xiaopa.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.base.xiaopa.Adapter.GroupAdapter;
import com.base.xiaopa.constant.GroupBean;
import com.base.xiaopa.util.GroupUtils;
import com.xiaopa.android.R;

import java.util.ArrayList;

/**
 * Created by Ivan on 2017/10/28.
 */

public class CommentActivity extends Fragment {
    View v;
    ListView lv_comment;
    LinearLayout ll_comment;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.event_comment, container, false);
        lv_comment = (ListView)v.findViewById(R.id.lv_comment);
        ll_comment = (LinearLayout) v.findViewById(R.id.ll_comment);

        ArrayList<GroupBean> allGroup = GroupUtils.getAllGroup(getContext());
        GroupAdapter groupAdapter = new GroupAdapter(getContext(),allGroup);
        lv_comment.setAdapter(groupAdapter);
        return v;
    }
}
