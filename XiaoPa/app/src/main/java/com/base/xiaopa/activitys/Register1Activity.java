package com.base.xiaopa.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.base.xiaopa.model.BaseModel;
import com.base.xiaopa.model.UserModel;
import com.xiaopa.android.R;

import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;


public class Register1Activity extends Activity implements View.OnClickListener {


    private EditText et_username;
    private EditText et_password;
    private Button btn_register;
    private EditText et_password_again;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mContext = this;
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_register = (Button) findViewById(R.id.btn_register2);
        et_password_again = (EditText) findViewById(R.id.et_password_again);
        btn_register.setOnClickListener(this);

    }

    /**
     * 注册
     */
    @OnClick(R.id.btn_register2)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register2://注册
                UserModel.getInstance().register(et_username.getText().toString(), et_password.getText().toString(), et_password_again.getText().toString(), new LogInListener() {
                    @Override
                    public void done(Object o, BmobException e) {
                        if (e == null) {
                            Intent intent =new Intent(Register1Activity.this,MainActivity.class);
                            startActivity(intent);
                        } else {
                            if (e.getErrorCode() == BaseModel.CODE_NOT_EQUAL) {
                                et_password_again.setText("");
                            }
                            Toast.makeText(mContext,"注册失败！请稍后再试",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;

        }
    }
}