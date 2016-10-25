package com.example.lzp.dynamic_expression.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.lzp.dynamic_expression.R;

public class DisplayActivity extends AppCompatActivity {

    public static int MODE_SHOW_NEW_CREATED = 1;
    public static int MODE_SHOW_HISTORY = 2;


    private int mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        mode = getIntent().getBundleExtra("MODE").getInt("MODE");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

}
