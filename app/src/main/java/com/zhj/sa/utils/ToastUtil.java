package com.zhj.sa.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zhanghongjun on 2017/7/11.
 */

public class ToastUtil {
    private static Toast toast = null;

    public static void showShortMsg(Context context, String ...args){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < args.length; i++){
            sb. append(args[i]);
        }
        String s = sb.toString();

        context = context.getApplicationContext();
        if(toast == null){
            toast = Toast.makeText(context,s ,Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    public static void showLongMsg(Context context,String msg){
        context = context.getApplicationContext();
        if(toast == null){
            toast = Toast.makeText(context,msg,Toast.LENGTH_LONG);
        }
        toast.show();
    }
}
