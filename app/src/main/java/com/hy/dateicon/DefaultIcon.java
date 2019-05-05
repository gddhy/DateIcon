package com.hy.dateicon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class DefaultIcon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Util.setDefaultICon(this);
        Intent intentServer = new Intent(this,UpdateService.class);
        startService(intentServer);
        Toast.makeText(this,"默认图标",Toast.LENGTH_LONG).show();
        finish();
    }
}
