package com.base.xiaopa;

import android.content.Context;

import cn.bmob.newim.event.MessageEvent;
import cn.bmob.newim.listener.BmobIMMessageHandler;

/**
 * 消息接收器
 *
 * Created by cldn1 on 2017/11/8.
 */
//TODO 集成：1.6、自定义消息接收器处理在线消息和离线消息
public class XiaoPaMessageHandle extends BmobIMMessageHandler {
    private Context context;

    public XiaoPaMessageHandle(Context context){
        this.context = context;
    }

    //在线消息
    @Override
    public void onMessageReceive(final MessageEvent event) {
        //当接收服务器发来的信息时，此方法被调用
        executeMessage(event);
    }


    /**
     * 处理信息
     * @param event
     */

    private void executeMessage(final MessageEvent event){
        //检查用户信息是否需要更新

    }
}
