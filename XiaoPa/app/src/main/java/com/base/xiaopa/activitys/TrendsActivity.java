package com.base.xiaopa.activitys;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.base.xiaopa.Adapter.TrendsAdapter;
import com.base.xiaopa.constant.TrendsBean;
import com.base.xiaopa.util.TrendsUtils;
import com.xiaopa.android.R;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

public class TrendsActivity extends Fragment implements View.OnClickListener {

    View v;
    ViewPager vp_pager;
    //    ListView lv_compete;
//    TextView SM;
    Button mCompete;
    Button mFellow;
    Button mHot;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.activity_trends, container, false);
//        lv_compete = (ListView) v.findViewById(R.id.lv_compete);
////        获取群组数据用list封装
//        ArrayList<TrendsBean> allCompete = TrendsUtils.getAllCompete(getContext());
//        //创建Adapter设置给listView
//        TrendsAdapter trendsAdapter = new TrendsAdapter(getContext(),allCompete);
//        lv_compete.setAdapter(trendsAdapter);
//
//        //设置点击事件
//        lv_compete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            //ListView的条目点击时会调用该方法
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(getActivity(),SummaryActivity.class);
//                startActivity(intent);
//            }
//        });
//        mDetails = (TextView) v.findViewById(R.id.item_compete_details);
//        mDetails.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                startActivity(intent);
//            }
//        });

//        SM = (TextView) v.findViewById(R.id.tv_summary);
//        SM.setText("我是第四个活动");
        BGABanner banner = (BGABanner) v.findViewById(R.id.banner1);
        List<View> views1 = new ArrayList<>();
//        List<String> views2= new ArrayList<>();
        views1.add(getPageView(R.drawable.ad_good_voice));
        views1.add(getPageView(R.drawable.ad_lanqiaobei));
        views1.add(getPageView(R.drawable.ad_marketing));
//        views2.add("我是第一个活动");
//        views2.add("我是第二个活动");
//        views2.add("我是第三个活动");
//        views2.add("我是第四个活动");
        banner.setData(views1);
        banner.setDelegate(new BGABanner.Delegate<ImageView, String>() {
            @Override
            public void onBannerItemClick(BGABanner banner, ImageView itemView,
                                          String model, int position) {
//                change(position);
            }
        });
        fragmentpage();
        return v;
    }
//
//    public void change(int position) {
//        if (position == 0) {
//            SM.setText("我是第一个活动");
//        } else if (position == 1) {
//            SM.setText("我是第二个活动");
//        } else if (position == 2) {
//            SM.setText("我是第三个活动");
//        } else if (position == 3) {
//            SM.setText("我是第四个活动");
//        }
//
//    }

    /**
     * banner
     **/
    private View getPageView(@DrawableRes int resid) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(resid);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    public void fragmentpage() {
        final ArrayList<Fragment> list = new ArrayList<Fragment>();
        list.add(new CompeteActivity());
        list.add(new FellowshipActivity());
        list.add(new HotActivity());
        mCompete = (Button) v.findViewById(R.id.bt_compete);
        mFellow = (Button) v.findViewById(R.id.bt_fellow);
        mHot = (Button) v.findViewById(R.id.bt_hot);

        mCompete.setOnClickListener(this);
        mFellow.setOnClickListener(this);
        mHot.setOnClickListener(this);

        FragmentStatePagerAdapter adapter_f = new FragmentStatePagerAdapter(getChildFragmentManager()) {

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public void finishUpdate(ViewGroup container) {
                super.finishUpdate(container);


                if (vp_pager.getCurrentItem() == 0) {
                    resetColor();
                    mCompete.setTextColor(Color.parseColor("#FFA127"));

                } else if (vp_pager.getCurrentItem() == 1) {
                    resetColor();
                    mFellow.setTextColor(Color.parseColor("#FFA127"));

                } else if (vp_pager.getCurrentItem() == 2) {
                    resetColor();
                    mHot.setTextColor(Color.parseColor("#FFA127"));
                }

            }
        };
        vp_pager = (ViewPager) v.findViewById(R.id.vp_mf4);
        vp_pager.setAdapter(adapter_f);
    }

    public void resetColor() {
        mCompete.setTextColor(Color.parseColor("#000000"));
        mFellow.setTextColor(Color.parseColor("#000000"));
        mHot.setTextColor(Color.parseColor("#000000"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_compete:
                vp_pager.setCurrentItem(0, true);
                break;
            case R.id.bt_fellow:
                vp_pager.setCurrentItem(1, true);
                break;
            case R.id.bt_hot:
                vp_pager.setCurrentItem(2, true);
                break;
            default:
                break;
        }
    }
}
