package com.base.xiaopa.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.base.xiaopa.model.UserModel;
import com.xiaopa.android.R;

import cn.bmob.newim.BmobIM;

/**
 * Created by Ivan on 2017/11/14.
 */

public class SetActivity extends Activity implements View.OnClickListener{
    private Button bt_unlogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        bt_unlogin = (Button) findViewById(R.id.bt_unlogin);
        bt_unlogin.setOnClickListener(this);
    }
    public void unlogin() {
        UserModel.getInstance().logout();
        //TODO 连接：3.2、退出登录需要断开与IM服务器的连接
        BmobIM.getInstance().disConnect();
        this.finish();
        Intent i=new Intent(this,Log_RegActivity.class);
        startActivity(i);

    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bt_unlogin:
                unlogin();
                break;
            default :
                break;
        }
    }
}
