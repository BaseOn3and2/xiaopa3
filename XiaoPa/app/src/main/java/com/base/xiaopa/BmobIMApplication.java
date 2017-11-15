package com.base.xiaopa;

import android.app.Application;

import com.orhanobut.logger.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import cn.bmob.newim.BmobIM;
/**
 * 初始化BmobNewIM SDK
 * Created by cldn1 on 2017/11/8.
 */
//TODO 集成：1.7、自定义Application，并在AndroidManifest.xml中配置
public class BmobIMApplication extends Application {

    private static BmobIMApplication INSTANCE;

    //创建一个实例，用于被直接调取
    public static BmobIMApplication INSTANCE(){
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //TODO 集成：1.8、初始化IM SDK，并注册消息接收器,只有主进程运行的时候才需要初始化
        if(getApplicationInfo().packageName.equals(getMyProcessName())){
            BmobIM.init(this);
            BmobIM.registerDefaultMessageHandler(new XiaoPaMessageHandle(this));
            Logger.init("BmobNewIMDemo");
           // UniversalImageLoader.initImageLoader(this);
        }

    }

    /**
     * 获取当前运行的进程名
     *
     * @return
     */
    public static String getMyProcessName() {
        try {
            File file = new File("/proc/" + android.os.Process.myPid() + "/" + "cmdline");
            BufferedReader mBufferedReader = new BufferedReader(new FileReader(file));
            String processName = mBufferedReader.readLine().trim();
            mBufferedReader.close();
            return processName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
