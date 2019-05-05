package com.hy.dateicon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class upDateIconActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean b = Util.setDateICon(this);
        Toast.makeText(this,(b?"已":"无需")+"更新图标",Toast.LENGTH_LONG).show();
        finish();
    }
}
