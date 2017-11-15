package com.base.xiaopa.activitys;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.base.xiaopa.model.UserModel;
import com.base.xiaopa.util.UserInfoUtil;
import com.mob.tools.utils.UIHandler;
import com.xiaopa.android.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * Created by cldn1 on 2017/11/14.
 */
public class LoginActivity extends Fragment implements View.OnClickListener
        ,PlatformActionListener,Handler.Callback {

    private EditText et_username;
    private EditText et_password;
    private CheckBox cd_rem;
    private Button bt_login;
    private ImageView qq_login;
    private ImageView weichat_login;
    private Context mContext;
    private boolean isrem;
    private String username;
    private String password;

    private Button bt_regster;
    View v;

    private static final int MSG_ACTION_CCALLBACK = 0;
    private ProgressDialog progressDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.activity_login,container,false);
        mContext = getContext();
        //获取控件
        et_username = (EditText)v.findViewById(R.id.username);
        et_password = (EditText)v.findViewById(R.id.user_password);
        cd_rem = (CheckBox) v.findViewById(R.id.cb_rem);
        bt_login = (Button)v.findViewById(R.id.bt_login);
        qq_login = (ImageView)v.findViewById(R.id.qq_login);
        weichat_login = (ImageView)v.findViewById(R.id.weichat_login);
        bt_regster = (Button)v.findViewById(R.id.bt_register1);
        //为控件设置点击事件
        bt_login.setOnClickListener(this);
        qq_login.setOnClickListener(this);
        weichat_login.setOnClickListener(this);
        bt_regster.setOnClickListener(this);

        //回显用户名及密码
        Map<String,String> map = UserInfoUtil.getUserInfo(mContext);
        if(map!=null){
            String username = map.get("username");
            String password = map.get("password");
            et_username.setText(username);
            et_password.setText(password);
            cd_rem.setChecked(true);
        }

        return v;
    }

    //创建一个Handler
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            boolean issuccess = (Boolean) msg.obj;
            if(issuccess){
                //判断是否记住密码，如果记住，将密码缓存到本地
                if(isrem){
                    boolean result = UserInfoUtil.saveUserInfo(mContext,username,password);
                    if(result){
                        Toast.makeText(mContext,"用户名及密码保存成功",Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(mContext,"用户名及密码保存失败",Toast.LENGTH_SHORT).show();
                    }


                }else{
                    Toast.makeText(mContext,"不保存密码及用户名",Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent();
                intent.setClass(getActivity(),MainActivity.class);
                startActivity(intent);

            }else{
                Toast.makeText(mContext,"用户名或密码错误，请重新输入",Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bt_login://登陆
                login();
                break ;
            case R.id.qq_login://这里添加QQ登陆
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.setPlatformActionListener(this);
                qq.SSOSetting(false);
                authorize(qq, 2);
                break;
            case R.id.weichat_login://这里添加微信登陆
                Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
                wechat.setPlatformActionListener(this);
                wechat.SSOSetting(false);
                authorize(wechat, 1);
                break;
            case R.id.guest:
                Intent intent =new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
                onDestroy();
                break;
            case R.id.bt_register1:
                onRegister();
            default :
                break;
        }
    }


    /**
     * 登陆
     */
    @OnClick((R.id.bt_login))
    public void login(){
        UserModel.getInstance().login(et_username.getText().toString(),
                et_password.getText().toString(), new LogInListener() {
                    @Override
                    public void done(Object o, BmobException e) {
                        if(e==null){
                            //登陆成功
                            Intent i=new Intent(getContext(),MainActivity.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(mContext,"登陆失败！请稍后再试",Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }

    /**
     * 注册
     */
    public void onRegister(){
        //跳转到注册页面
        Intent i=new Intent(getContext(),Register1Activity.class);
        startActivity(i);
    }

    //授权
    private void authorize(Platform plat, int type) {
        switch (type) {
            case 1:
                showProgressDialog(getString(R.string.opening_wechat));
                break;
            case 2:
                showProgressDialog(getString(R.string.opening_qq));
                break;
            case 3:
                showProgressDialog(getString(R.string.opening_blog));
                break;
        }
        if (plat.isValid()) { //如果授权就删除授权资料
            plat.removeAccount();
        }
        plat.showUser(null);//授权并获取用户信息
    }



    //登陆授权成功的回调
    @Override
    public void onComplete(Platform platform, int action, HashMap<String, Object> res) {
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 1;
        msg.arg2 = action;
        msg.obj = platform;
        UIHandler.sendMessage(msg, this);   //发送消息

    }

    //登陆授权错误的回调
    @Override
    public void onError(Platform platform, int action, Throwable t) {
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 2;
        msg.arg2 = action;
        msg.obj = t;
        UIHandler.sendMessage(msg, this);
    }

    //登陆授权取消的回调
    @Override
    public void onCancel(Platform platform, int action) {
        Message msg = new Message();
        msg.what = MSG_ACTION_CCALLBACK;
        msg.arg1 = 3;
        msg.arg2 = action;
        msg.obj = platform;
        UIHandler.sendMessage(msg, this);
    }

    //登陆发送的handle消息在这里处理
    @Override
    public boolean handleMessage(Message message) {
        hideProgressDialog();
        switch (message.arg1) {
            case 1: { // 成功
                Toast.makeText(getActivity(), "授权登陆成功", Toast.LENGTH_SHORT).show();

                //获取用户资料
                Platform platform = (Platform) message.obj;
                String userId = platform.getDb().getUserId();//获取用户账号
                String userName = platform.getDb().getUserName();//获取用户名字
                String userIcon = platform.getDb().getUserIcon();//获取用户头像
                //获取用户性别，m = 男, f = 女，如果微信没有设置性别,默认返回null
                String userGender = platform.getDb().getUserGender();
                //跳转到主界面
                Intent intent = new Intent();
                intent.setClass(getActivity(),GroupActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "用户信息为--用户名：" + userName +
                        "性别：" + userGender, Toast.LENGTH_SHORT).show();

                //下面就可以利用获取的用户信息登录自己的服务器或者做自己想做的事啦!
                //。。。

            }
            break;
            case 2: { // 失败
                Toast.makeText(getActivity(), "授权登陆失败", Toast.LENGTH_SHORT).show();
            }
            break;
            case 3: { // 取消
                Toast.makeText(getActivity(), "授权登陆取消", Toast.LENGTH_SHORT).show();
            }
            break;
        }
        return false;
    }

    //显示dialog
    public void showProgressDialog(String message) {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(message);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

    //隐藏dialog
    public void hideProgressDialog() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }
}

