package com.example.lzp.dynamic_expression.activity;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;

import com.example.lzp.dynamic_expression.R;
import com.example.lzp.dynamic_expression.custom_view.CameraInterface;
import com.example.lzp.dynamic_expression.custom_view.CameraSurfaceView;
import com.example.lzp.dynamic_expression.utils.DisplayUtil;

public class CameraActivity extends Activity implements CameraInterface.camOpenOverCallBack{
    private static final String TAG = "CameraActivity";
    CameraSurfaceView surfaceView = null;
    Button btn_shot = null;
    float previewRate = -1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
        setContentView(R.layout.activity_camera);
        btn_shot = (Button)findViewById(R.id.btn_shot);

        //拍照按钮事件
        btn_shot.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                CameraInterface.getInstance().doTakePicture();
            }
        });
    }

    //初始化屏幕的参数
    private void initViewParams(){
        LayoutParams params = surfaceView.getLayoutParams();
        Point p = DisplayUtil.getScreenMetrics(this);
        params.width = p.x;
        params.height = p.y;
        previewRate = DisplayUtil.getScreenRate(this);
        surfaceView.setLayoutParams(params);
    }

    //如果Camera已经Open，就会自动调用该方法,开始初始化SurfaceView和预览的参数
    public void cameraHasOpened() {
        // TODO Auto-generated method stub
        SurfaceHolder holder = surfaceView.getSurfaceHolder();
        CameraInterface.getInstance().doStartPreview(holder, previewRate);
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG,"onPause");
        //关闭相机
        CameraInterface.getInstance().doStopCamera();
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG,"onResume!");
        new Thread(new Runnable(){
            public void run(){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                //开启相机
                CameraInterface.getInstance().doOpenCamera(CameraActivity.this);
            }
        }).start();

        surfaceView = (CameraSurfaceView)findViewById(R.id.camera_surfaceview);
        initViewParams();
    }
}
