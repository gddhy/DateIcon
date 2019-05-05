package com.hy.dateicon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class upDateIcon extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.setDateICon(this);
        finish();
    }
}
