package com.example.lzp.dynamic_expression.custom_view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.SurfaceHolder;

import com.example.lzp.dynamic_expression.utils.CamParaUtil;
import com.example.lzp.dynamic_expression.utils.ImageUtil;
import com.example.lzp.dynamic_expression.utils.MyApplication;

import java.io.IOException;
import java.util.List;

/**
 * Created by lzp on 2016/10/25.
 */

public class CameraInterface {

    private static final String TAG = "CameraInterface";
    private static CameraInterface mCameraInterface;
    float mPreviewRate = -1f;
    private Camera mCamera;
    private Camera.Parameters mParams;
    private boolean isPreviewing = false;

    //接口封装了一个方法，用来开启相机后调用
    public interface camOpenOverCallBack{
        void cameraHasOpened();
    }

    //获取该类的实例用以调用其它相机相关的方法
    public static synchronized CameraInterface getInstance(){
        if(mCameraInterface == null)
            mCameraInterface = new CameraInterface();
        return mCameraInterface;
    }

    //打开相机并调用camOpenOverCallBack接口中的方法
    public void doOpenCamera(camOpenOverCallBack callBack){
        Log.i(TAG, "Camera open");
        mCamera = Camera.open();
        Log.i(TAG, "Camera open done");
        callBack.cameraHasOpened();
    }

    //关闭相机并关闭预览
    public void doStopCamera(){
        if(null != mCamera)
        {
            Log.i(TAG,"doStopCameraStart");
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            isPreviewing = false;
            mPreviewRate = -1f;
            mCamera.release();
            mCamera = null;
            Log.i(TAG,"doStopCameraOver");
        }
    }

    //初始化预览参数并开始预览
    public void doStartPreview(SurfaceHolder holder, float previewRate){
        Log.i(TAG, "doStartPreview...");
        if(isPreviewing){
            mCamera.stopPreview();
            return;
        }
        if(mCamera != null){

            mParams = mCamera.getParameters();
            mParams.setPictureFormat(PixelFormat.JPEG);//设置拍照后存储的图片格式
            CamParaUtil.getInstance().printSupportPictureSize(mParams);
            CamParaUtil.getInstance().printSupportPreviewSize(mParams);
            //设置PreviewSize和PictureSize
            Size pictureSize = CamParaUtil.getInstance().getPropPictureSize(
                    mParams.getSupportedPictureSizes(),previewRate, 4130);
            mParams.setPictureSize(pictureSize.width, pictureSize.height);
            Size previewSize = CamParaUtil.getInstance().getPropPreviewSize(
                    mParams.getSupportedPreviewSizes(), previewRate, 4130);
            mParams.setPreviewSize(previewSize.width, previewSize.height);

            mCamera.setDisplayOrientation(90);

            //对焦模式
            CamParaUtil.getInstance().printSupportFocusMode(mParams);
            List<String> focusModes = mParams.getSupportedFocusModes();
            if(focusModes.contains("continuous-video")){
                mParams.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
            }
            mCamera.setParameters(mParams);

            try {
                mCamera.setPreviewDisplay(holder);
                mCamera.startPreview();//开启预览
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            isPreviewing = true;
            mPreviewRate = previewRate;

            mParams = mCamera.getParameters(); //重新get一次,打印最终的配置
            Log.i(TAG, "最终设置:PreviewSize--With = " + mParams.getPreviewSize().width
                    + "Height = " + mParams.getPreviewSize().height);
            Log.i(TAG, "最终设置:PictureSize--With = " + mParams.getPictureSize().width
                    + "Height = " + mParams.getPictureSize().height);
        }
    }

    //拍照方法
    public void doTakePicture(){
        if(isPreviewing && (mCamera != null)){
            mCamera.takePicture(null, null, mJpegPictureCallback);
        }
    }

    //拍照的回调方法，很重要
    Camera.PictureCallback mJpegPictureCallback = new Camera.PictureCallback()
    {
        public void onPictureTaken(byte[] data, Camera camera) {
            // TODO Auto-generated method stub
            Log.i(TAG, "myJpegCallback:onPictureTaken");
            Bitmap b = null;
            if(null != data){
                b = BitmapFactory.decodeByteArray(data, 0, data.length);
                mCamera.stopPreview();
                isPreviewing = false;
            }

            if(null != b)
            {
                Bitmap rotaBitmap = ImageUtil.getRotateBitmap(b, 90.0f);
                //rotaBitmap是目标bitmap，接下来这里可以开启新activity,并将rotaBitmap传进Application里
                MyApplication.setBitmapMaterial(rotaBitmap);
                //start create dynamic picture activity
                Log.i("customView","start new activity");
            }

            mCamera.startPreview();
            isPreviewing = true;

        }
    };

}
