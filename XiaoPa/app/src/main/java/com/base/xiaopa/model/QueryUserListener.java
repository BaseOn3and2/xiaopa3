package com.base.xiaopa.model;

import com.base.xiaopa.bean.User;

import cn.bmob.newim.listener.BmobListener1;
import cn.bmob.v3.exception.BmobException;


/**
 * Created by cldn1 on 2017/11/10.
 */
public abstract class QueryUserListener extends BmobListener1<User> {
    public abstract void done(User s, BmobException e);

    @Override
    protected void postDone(User o, BmobException e) {
        done(o, e);
    }
}
