package com.base.xiaopa.activitys;

import android.os.Handler;
import android.os.Looper;
import android.os.Bundle;

import com.base.xiaopa.base.BaseActivity;
import com.base.xiaopa.bean.User;
import com.base.xiaopa.model.UserModel;
import com.xiaopa.android.R;

/**
 * 启动界面
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //不是主线程更新Ui,需要加Looper.getMainLooper()
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                User user = UserModel.getInstance().getCurrentUser();
                if(user == null){
                  startActivity(Log_RegActivity.class,null,true);
                }else{
                   startActivity(MainActivity.class,null,true);
                }
            }
        },1000);
    }
}
