package com.example.lzp.dynamic_expression.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.example.lzp.dynamic_expression.R;
import com.example.lzp.dynamic_expression.utils.MyApplication;

public class WaitCreateActivity extends AppCompatActivity {

    private static int SUCCESS = 0;
    private static int FAIL = -1;


    private ProgressBar progressbar;
    private Bitmap figurePhoto;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == SUCCESS){
                progressbar.setVisibility(View.GONE);
                Intent intent = new Intent(WaitCreateActivity.this,DisplayActivity.class);
                startActivity(intent);
                WaitCreateActivity.this.finish();
            }else if(msg.what == FAIL){
                //提示失败并且回退到上一个界面。
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_create);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressbar = (ProgressBar)findViewById(R.id.progress_bar);
        //获取intent 并从中得出其中的bitmap
        task.execute();
    }

    private AsyncTask<Void,Void,Void> task = new AsyncTask<Void, Void, Void>() {
        @Override
        protected Void doInBackground(Void... params) {
            //get photo
            //get template
            // Bitmap bitmap = WaitPhotoCreatieActivity.this.getIntent().getExtras().getParcelable("bitmap");

            Bitmap bitmap = MyApplication.getBitmapMaterial();
            //加载默认模板
            /*DataModel.setDefaultModels(WaitPhotoCreatieActivity.this);
            MyApplication.setAnimaitionArray(PhotoFactory.createDynamicPhoto(DataModel.getDefaultDataModels(),bitmap));*/
            handler.sendEmptyMessage(SUCCESS);
            return null;
        }
    };

}
