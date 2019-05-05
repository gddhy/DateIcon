package com.hy.dateicon;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class SetDefaultAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showDialog();
    }

    private void showDialog(){
        //输入默认日历app
        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor[] editor = new SharedPreferences.Editor[1];
        final EditText et_data = new EditText(this);
        final String defaultPackage = "com.android.calendar";
        et_data.setText(pref.getString("defaultCalendarApp",null));
        new AlertDialog.Builder(this)
                .setTitle("请输入默认日历app包名")
                .setMessage(".通常默认日历为"+defaultPackage+"\n.设置后如果想修改可以通过shortcut打开本界面")
                .setView(et_data)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @SuppressLint("CommitPrefEdits")
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //按下确定键后的事件
                        if (et_data.getText().toString().length() == 0 ) {
                            Toast.makeText(getApplicationContext(), "未输入内容", Toast.LENGTH_LONG).show();
                        } else {
                            String pkgName = et_data.getText().toString();
                            if  (IsApp(getApplicationContext(),pkgName)){
                                editor[0] = pref.edit();
                                editor[0].putString("defaultCalendarApp",pkgName);
                                editor[0].apply();
                                Toast.makeText(getApplicationContext(), "已设置"+pkgName+"为默认日历应用", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "应用不存在，请重新输入", Toast.LENGTH_LONG).show();
                            }
                        }
                        finish();
                    }
                })
                .setNeutralButton("使用默认值", new DialogInterface.OnClickListener() {
                    @SuppressLint("CommitPrefEdits")
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        editor[0] = pref.edit();
                        editor[0].putString("defaultCalendarApp",defaultPackage);
                        editor[0].apply();
                        Toast.makeText(getApplicationContext(), "已使用默认值", Toast.LENGTH_LONG).show();
                        finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
        }).show();
    }

    //检测app是否存在 存在true 否则false
    static boolean IsApp(Context context, String packageName){
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        return intent != null;
    }
}
