package com.base.xiaopa.model;

import cn.bmob.newim.listener.BmobListener1;
import cn.bmob.v3.exception.BmobException;

/**
 * Created by cldn1 on 2017/11/10.
 */
public  abstract class UpdateCacheListener extends BmobListener1 {
   public abstract  void done(BmobException e);

    @Override
    protected void postDone(Object o, BmobException e) {
        done(e);
    }
}
