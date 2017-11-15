package com.base.xiaopa.activitys;

import android.content.Intent;
import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;

import android.support.v4.view.ViewPager;


import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;


import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.base.xiaopa.base.BaseActivity;
import com.base.xiaopa.bean.User;
import com.base.xiaopa.event.RefreshEvent;
import com.base.xiaopa.ui.ConversationFragment;
import com.base.xiaopa.ui.SearchUserActivity;
import com.base.xiaopa.util.IMMLeaks;
import com.base.xiaopa.view.NoScrollViewPager;
import com.base.xiaopa.view.SlidingMenu;
import com.orhanobut.logger.Logger;
import com.xiaopa.android.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.bean.BmobIMUserInfo;
import cn.bmob.newim.core.ConnectionStatus;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.newim.listener.ConnectStatusChangeListener;
import cn.bmob.newim.notification.BmobNotificationManager;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import de.greenrobot.event.EventBus;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ViewPager mViewPager; //用于放置界面切换
    private List<View> mViews = new ArrayList<View>();//用来存放每个功能展示不同的页面

    /**
     * Tab 以及按钮
     */


    private ImageButton mHomePageImg;
    private ImageButton mGroupChatImg;
    private ImageButton mTrendsImg;
    private ImageButton mMallImg;
    private ImageButton mMeImg;
    private SlidingMenu mMenu;
    private ImageView mSearch;
    private ImageView mAdd;
    private TextView mTitle;

    ViewPager vp_pager;
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdd = (ImageView)findViewById(R.id.bt_add);
//        mMenu =(SlidingMenu)findViewById(R.id.id_menu);
        fragmentpage();

        final User user = BmobUser.getCurrentUser(User.class);
        //TODO 连接：3.1，登陆成功，注册成功或处于登陆状态重新打开应用后执行IM服务器的操作
        //判断用户是否登陆，并且连接状态不是已连接，则进行连接操作
        if(!TextUtils.isEmpty(user.getObjectId())&& BmobIM.getInstance().getCurrentStatus().getCode()!= ConnectionStatus.CONNECTED.getCode()){
            BmobIM.connect(user.getObjectId(), new ConnectListener() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        //服务器连接成功就发送一个更新事件，同步更新会话及主页的小红点
                        EventBus.getDefault().post(new RefreshEvent());
                        //TODO 会话：2.7、更新用户资料，用于在会话页面、聊天页面以及个人信息页面显示
                        BmobIM.getInstance().
                                updateUserInfo(new BmobIMUserInfo(user.getObjectId(),
                                        user.getUsername(), user.getAvatar()));
                    } else {
                        toast(e.getMessage());
                    }
                }
            });
            //TODO 连接：3.3，监听连接状态，可通过BmobIM.getInstance().getCurrentStatus()来获取当前的长连接状态
            BmobIM.getInstance().setOnConnectStatusChangeListener(new ConnectStatusChangeListener() {
                @Override
                public void onChange(ConnectionStatus status) {
                    toast(status.getMsg());
                    Logger.i(BmobIM.getInstance().getCurrentStatus().getMsg());
                }
            });
        }
        //解决leancanary提示InputMethodManager内存泄露的问题
        IMMLeaks.fixFocusedViewLeak(getApplication());

    }
    public void fragmentpage()
    {
        final ArrayList<Fragment> list =new ArrayList<Fragment>();
        list.add(new ConversationFragment());
        list.add(new TrendsActivity());
        list.add(new HomeActivity());
        list.add(new StoreActivity());
        list.add(new MeActivity());
        mSearch = (ImageView) findViewById(R.id.iv_search);
        mAdd =(ImageView)findViewById(R.id.bt_add);
        mTitle =(TextView)findViewById(R.id.tv_title);

        mHomePageImg= (ImageButton) findViewById(R.id.id_home_img);
        mTrendsImg= (ImageButton) findViewById(R.id.id_trends_img);
        mMallImg= (ImageButton) findViewById(R.id.id_mall_img);
        mMeImg= (ImageButton) findViewById(R.id.id_me_img);
        mGroupChatImg= (ImageButton) findViewById(R.id.id_chat_img);

        mSearch.setOnClickListener(this);
        mAdd.setOnClickListener(this);
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

                if(vp_pager.getCurrentItem()==0)
                {
                    resetImg();
                    mSearch.setVisibility(View.VISIBLE);
                    mAdd.setVisibility(View.VISIBLE);
                    mAdd.setImageResource(R.drawable.add);
                    mTitle.setText("群聊");
                    flag = 0;
                    mGroupChatImg.setBackgroundResource(R.drawable.chat_orange2);
                }else if(vp_pager.getCurrentItem()==1)
                {
                    resetImg();
                    mSearch.setVisibility(View.GONE);
                    mAdd.setVisibility(View.GONE);
                    mTitle.setText("动态");
                    mTrendsImg.setBackgroundResource(R.drawable.trends_orange2);

                }else if(vp_pager.getCurrentItem()==2)
                {
                    resetImg();
                    mSearch.setVisibility(View.GONE);
                    mAdd.setVisibility(View.GONE);
                    mTitle.setText("主页");
                    mHomePageImg.setBackgroundResource(R.drawable.home_orange2);

                }else  if(vp_pager.getCurrentItem()==3)
                {
                    resetImg();
                    mSearch.setVisibility(View.VISIBLE);
                    mAdd.setVisibility(View.VISIBLE);
                    mAdd.setImageResource(R.drawable.car);
                    flag = 3;
                    mTitle.setText("商城");
                    mMallImg.setBackgroundResource(R.drawable.mall_orange2);

                }else  if(vp_pager.getCurrentItem()==4){
                    resetImg();
                    mSearch.setVisibility(View.GONE);
                    mAdd.setVisibility(View.VISIBLE);
                    mAdd.setImageResource(R.drawable.set);
                    flag=4;
                    mTitle.setText("我的");
                    mMeImg.setBackgroundResource(R.drawable.me_orange2);
                }


            }
        };
        vp_pager= (ViewPager) findViewById(R.id.vp_mf);
        vp_pager.setAdapter(adapter_f);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_search:
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_add:
                if (0==flag){
                    Intent i0 = new Intent(MainActivity.this,SearchUserActivity.class);
                    startActivity(i0);
                }else if (3==flag){
                    Intent i = new Intent(MainActivity.this,TrolleyActivity.class);
                    startActivity(i);
                }else if (4==flag ){
                    Intent i2 = new Intent(MainActivity.this,SetActivity.class);
                    startActivity(i2);
                }
        }

    }
    public void groupChat(View v)
    {
        vp_pager.setCurrentItem(0,true);

    }
    public  void trends(View v){
        vp_pager.setCurrentItem(1,true);
    }
    public void home(View v){
        vp_pager.setCurrentItem(2,true);
    }
    public void shop(View v){
        vp_pager.setCurrentItem(3,true);
    }
    public void me(View v){
        vp_pager.setCurrentItem(4,true);
    }


    public void resetImg()
    {

        mHomePageImg.setBackgroundResource(R.drawable.home_black);
        mTrendsImg.setBackgroundResource(R.drawable.trends_black);
        mMallImg.setBackgroundResource(R.drawable.mall_black);
        mMeImg.setBackgroundResource(R.drawable.me_black);
        mGroupChatImg.setBackgroundResource(R.drawable.chat_black);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //每次进来应用都检查会话和好友请求的情况
        //checkRedPoint();
        //进入应用后，通知栏应取消
        BmobNotificationManager.getInstance(this).cancelNotification();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //清理导致内存泄露的资源
        BmobIM.getInstance().clear();
    }

}
