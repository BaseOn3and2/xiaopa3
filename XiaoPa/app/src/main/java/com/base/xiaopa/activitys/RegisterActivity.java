package com.base.xiaopa.activitys;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.base.xiaopa.bean.User;
import com.xiaopa.android.R;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;


public class RegisterActivity extends Fragment implements View.OnClickListener{

    private Button register;
    private TextView getCode;
    private EditText code;
    private EditText Tel;
    String mPhone;
    View v;

    EventHandler eventHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.activity_register_1,container,false);
        Tel = (EditText) v.findViewById(R.id.et_Tel);
        getCode = (TextView) v.findViewById(R.id.tv_getCode);
        code = (EditText) v.findViewById(R.id.ed_code);
        register = (Button) v.findViewById(R.id.bt_register);

        /**
         * 点击事件
         */

        getCode.setOnClickListener(this);
        register.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_getCode:
                getCode();
                break;
            case R.id.bt_register:
                submitCode();
                break;
            default:
                break;
        }
    }
    /**
     * 获取验证码
     */
    private boolean getCode() {

        mPhone=Tel.getText().toString();  //获取手机号
        BmobSMS.requestSMSCode(mPhone,"123",new QueryListener<Integer>(){
            @Override
            public void done(Integer integer, BmobException e) {
                if(e==null) {
                    MyCountDownTimer myCountDownTimer = new MyCountDownTimer(60000, 1000);
                    myCountDownTimer.start();
                    Toast.makeText(getContext(), "验证码发送成功!", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getContext(), "验证码发送失败!\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        return true;
    }
    /**
     * 提交验证码
     */
    private void submitCode() {
        String str=code.getText().toString();  //获取验证码
        BmobUser.signOrLoginByMobilePhone(mPhone, str, new LogInListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(user!=null){
                    Toast.makeText(getContext(),"注册成功！",Toast.LENGTH_LONG).show();
                    Intent i=new Intent(getContext(),MainActivity.class);
                    startActivity(i);

                }else{
                    Toast.makeText(getContext(),"注册失败!\n"+e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });



    }
    /**
     * 事务处理
     */
    private void MSG() {

    }

    /**
     * 倒计时
     */
    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //计时过程
        @Override
        public void onTick(long l) {
            //防止计时过程中重复点击
            getCode.setClickable(false);
            getCode.setText((l / 1000) + "s");
        }

        //计时完毕的方法
        @Override
        public void onFinish() {
            //重新给Button设置文字
            getCode.setText("重新获取");
            //设置可点击
            getCode.setClickable(true);
        }
    }


    /**
     * 销毁事务处理
     */
    public void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }


}