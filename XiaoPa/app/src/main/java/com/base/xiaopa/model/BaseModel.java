package com.base.xiaopa.model;

import android.content.Context;

import com.base.xiaopa.BmobIMApplication;

/**
 * Created by cldn1 on 2017/11/8.
 */
public class BaseModel {
    public int CODE_NULL = 1000;
    public static int CODE_NOT_EQUAL = 1001;
    public static final int DEFAULT_LIMIT=20;
    public Context getContext(){
        return BmobIMApplication.INSTANCE();
    }

}
