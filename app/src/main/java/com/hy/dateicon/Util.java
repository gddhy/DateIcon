package com.hy.dateicon;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static boolean setDateICon(Context context){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        int dateNow = pref.getInt("NowDate",0);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat systemDate = new SimpleDateFormat("dd");
        int date = Integer.parseInt(systemDate.format(new Date()));
        if (dateNow != date) {
            for (int i = 0; i <= 31; i++) {
                setApplicationIcon(context, new ComponentName(context, context.getPackageName() + ".Date" + i + "Activity"), date == i);
            }
            SharedPreferences.Editor editor = pref.edit();
            editor.putInt("NowDate",date);
            editor.apply();
            return true;
        } else {
            return false;
        }
    }

    public static void setDefaultICon(Context context){
        for (int i = 0 ; i <= 31 ; i++){
            setApplicationIcon(context,new ComponentName(context,context.getPackageName()+".Date"+i+"Activity"),0==i);
        }
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putInt("NowDate",0);
        editor.apply();
    }

    //程序动态图标设置
    private static void setApplicationIcon(Context context,ComponentName componentName,boolean isShow){
        if (isShow) {
            enableComponent(context,componentName);
        } else {
            disableComponent(context,componentName);
        }
    }

    /**
     * 通过PackageManager 的setComponentEnabledSetting方法控制组件的开启禁用。
     * 开启
     * @param componentName
     */
    private static void enableComponent(Context context, ComponentName componentName){

        context.getPackageManager().setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    /**
     * 禁用
     * @param componentName
     */
    private static void disableComponent(Context context, ComponentName componentName){
        context.getPackageManager().setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED ,
                PackageManager.DONT_KILL_APP);
    }//https://blog.csdn.net/androidzf/article/details/80749371
}
