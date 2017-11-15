package com.base.xiaopa.util;

/**
 * Created by cldn1 on 2017/11/13.
 */
public class Util {
    public static boolean checkSdCard() {
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }
}
