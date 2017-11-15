package com.base.xiaopa.activitys;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.base.xiaopa.entity.MyDialog;
import com.base.xiaopa.view.NoScrollViewPager;
import com.xiaopa.android.R;

import java.util.ArrayList;

import static com.xiaopa.android.R.color.gray;
import static com.xiaopa.android.R.color.grey;

/**
 * Created by Ivan on 2017/10/3.
 */

public class SummaryActivity extends FragmentActivity implements View.OnClickListener {

    Button JoinIn;
    AlertDialog.Builder builder;
    NoScrollViewPager vp_pager;
    Button introduce;
    Button comment;
    Button imgShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        fragmentpage();
    }


    public void fragmentpage()
    {
        JoinIn = (Button) findViewById(R.id.bt_joinIn);
        JoinIn.setOnClickListener(this);
        final ArrayList<Fragment> list =new ArrayList<Fragment>();
        list.add(new IntroduceActivity());
        list.add(new CommentActivity());
        list.add(new ImgShowActivity());
        introduce = (Button) findViewById(R.id.bt_introduce);
        comment = (Button) findViewById(R.id.bt_comment);
        imgShow = (Button)findViewById(R.id.bt_imgshow);

        FragmentStatePagerAdapter adapter_f=new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public void finishUpdate(ViewGroup container) {
                super.finishUpdate(container);
                if(vp_pager.getCurrentItem()==0) {
                    resetColor();
                    introduce.setTextColor(Color.parseColor("#FFA127"));
                }else if(vp_pager.getCurrentItem()==1) {
                    resetColor();
                    comment.setTextColor(Color.parseColor("#FFA127"));
                }else if(vp_pager.getCurrentItem()==2) {
                    resetColor();
                    imgShow.setTextColor(Color.parseColor("#FFA127"));
                }
            }
        };
        vp_pager= (NoScrollViewPager) findViewById(R.id.vp_mf2);
        vp_pager.setAdapter(adapter_f);
    }
    public void introduce(View v)
    {
        vp_pager.setCurrentItem(0,true);
    }
    public  void comment(View v){
        vp_pager.setCurrentItem(1,true);
    }
    public void Show(View v){
        vp_pager.setCurrentItem(2,true);
    }

    public void onClickSimple() {
        builder = new MyDialog().simpleDialog(this,null,"确定参加该活动");
        builder.setPositiveButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        })
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.create().show();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_joinIn:
                onClickSimple();
                break;
            default:
                break;
        }
    }

    public void resetColor()
    {
        introduce.setTextColor(Color.parseColor("#000000"));
        comment.setTextColor(Color.parseColor("#000000"));
        imgShow.setTextColor(Color.parseColor("#000000"));
    }
}
