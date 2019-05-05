package com.hy.dateicon;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Date0Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String packageName = pref.getString("defaultCalendarApp",null);
        boolean b;
        if (packageName == null){
            b = false;
        } else {
            b = openDefaultApp(this,packageName);
        }
        Util.setDateICon(this);
        if (!b){
            Intent intent = new Intent(Date0Activity.this, SetDefaultAppActivity.class);
            startActivity(intent);
        }
        finish();
    }

    //打开默认app
    static boolean openDefaultApp(Context context, String packageName){
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            return true;
        } else {
            return false;
            //Toast.makeText(context,"请通过shortcut设置默认app",Toast.LENGTH_LONG).show();
        }
    }
}
