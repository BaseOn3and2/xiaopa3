package com.base.xiaopa.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaopa.android.R;

/**
 * Created by Ivan on 2017/11/13.
 */

public class SearchActivity extends AppCompatActivity  {
    ImageView mSearch;
    ImageView mBack;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mBack = (ImageView) findViewById(R.id.tv_left);
//        mBack.setOnClickListener(this);

    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.tv_left:
//                Intent i = new Intent(SearchActivity.this,MainActivity.class);
//                startActivity(i);
//
//        }
//    }
}
