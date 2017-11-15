package com.base.xiaopa.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.base.xiaopa.Config;
import com.orhanobut.logger.Logger;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**基类
 * Created by cldn1 on 2017/11/10.
 */
public class BaseActivity extends FragmentActivity{
    @Override
    protected void onStart() {
        super.onStart();
        /**
         * EventBus是一款针对Android优化的发布/订阅事件总线,
         * 主要功能是替代Intent,Handler,BroadCast在Fragment，Activity，Service，
         * 线程之间传递消息.优点是开销小，代码更优雅。
         *
         *  ButterKnife是一个专注于Android系统的View、Resource、Action注入框架。
         *  简化了我们需要调用大量的findViewById以及setOnClickListener等代码
         */
        //在订阅事件的activity中注册
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        //在订阅事件的activity中取消注册
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        //对ButterKnife进行绑定
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //需要对ButterKnife解绑
        ButterKnife.unbind(this);
    }

    public void onEvent(Boolean empty){

    }

    protected void initView() {}

    protected void runOnMain(Runnable runnable) {
        runOnUiThread(runnable);
    }

    protected final static String NULL = "";
    private Toast toast;

    public void toast(final Object obj) {
        try {
            runOnMain(new Runnable() {

                @Override
                public void run() {
                    if (toast == null)
                        toast = Toast.makeText(BaseActivity.this, NULL,Toast.LENGTH_SHORT);
                    toast.setText(obj.toString());
                    toast.show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //封装跳转方法
    public void startActivity(Class<? extends Activity> target, Bundle bundle,boolean finish){
        Intent intent = new Intent();
        intent.setClass(this,target);
        if(bundle !=null) {
            //intent.putExtra()这个方法来放入自己要传递的数据，然后再另一个地方使用getxxx()来获取
            intent.putExtra(getPackageName(), bundle);
        }
            startActivity(intent);
            if(finish){
                //在你的activity动作完成的时候，或者Activity需要关闭的时候，调用此方法。
                finish();
            }

    }

    public Bundle getBundle(){
        if(getIntent() != null && getIntent().hasExtra(getPackageName())){
            return getIntent().getBundleExtra(getPackageName());
        }else{
            return null;
        }
    }

    /**
     * 隐藏软键盘
     */
    public void hideSoftInputView(){
        InputMethodManager manager = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**隐藏软键盘-一般是EditText.getWindowToken()
     * @param token
     */
    public void hideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token,InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**Log日志
     * @param msg
     */
    public void log(String msg){
        if(Config.DEBUG){
            Logger.i(msg);
        }
    }

}
