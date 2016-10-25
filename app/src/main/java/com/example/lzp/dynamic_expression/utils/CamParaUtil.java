package com.example.lzp.dynamic_expression.utils;

import android.hardware.Camera;
import android.util.Log;
import android.hardware.Camera.Size;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;





/**
 * Created by lzp on 2016/10/25.
 */

public class CamParaUtil {
    private static final String TAG = "CamParaUtil";
    private CameraSizeComparator sizeComparator = new CameraSizeComparator();
    private static CamParaUtil myCamPara = null;
    private CamParaUtil(){

    }
    public static CamParaUtil getInstance(){
        if(myCamPara == null){
            myCamPara = new CamParaUtil();
            return myCamPara;
        }
        else{
            return myCamPara;
        }
    }

    /**
     * 得到适当的预览大小
     * @param list
     * @param th
     * @param minWidth
     * @return
     */
    public Camera.Size getPropPreviewSize(List<Camera.Size> list, float th, int minWidth){
        Collections.sort(list, sizeComparator);

        int i = 0;
        for(Size s:list){
            if((s.width >= minWidth) && equalRate(s, th)){
                Log.i(TAG, "PreviewSize:w = " + s.width + "h = " + s.height);
                break;
            }
            i++;
        }
        if(i == list.size()){
            i = list.size()-1;
        }
        return list.get(i);
    }

    /**
     * 得到适当的图片大小
     * @param list
     * @param th
     * @param minWidth
     * @return
     */
    public Size getPropPictureSize(List<Camera.Size> list, float th, int minWidth){
        Collections.sort(list, sizeComparator);

        int i = 0;
        for(Size s:list){
            if((s.width >= minWidth) && equalRate(s, th)){
                Log.i(TAG, "PictureSize : w = " + s.width + "h = " + s.height);
                break;
            }
            i++;
        }
        if(i == list.size()){
            i = list.size()-1;
        }
        return list.get(i);
    }

    /**
     * 所谓的误差
     * @param s
     * @param rate
     * @return
     */
    public boolean equalRate(Size s, float rate){
        float r = (float)(s.width)/(float)(s.height);
        if(Math.abs(r - rate) <= 0.03)
        {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * 自定义比较器,用于list的排序
     */
    public  class CameraSizeComparator implements Comparator<Camera.Size> {
        public int compare(Size lhs, Size rhs) {
            // TODO Auto-generated method stub
            if(lhs.width == rhs.width){
                return 0;
            }
            else if(lhs.width > rhs.width){
                return 1;
            }
            else{
                return -1;
            }
        }

    }

    /**
     * 在log里打印出手机支持的预览大小
     * @param params
     */
    public  void printSupportPreviewSize(Camera.Parameters params){
        List<Size> previewSizes = params.getSupportedPreviewSizes();
        for(int i=0; i< previewSizes.size(); i++){
            Size size = previewSizes.get(i);
            Log.i(TAG, "previewSizes:width = "+size.width+" height = "+size.height);
        }

    }

    /**
     * 在log里打印出手机支持的图片大小
     * @param params
     */
    public  void printSupportPictureSize(Camera.Parameters params){
        List<Size> pictureSizes = params.getSupportedPictureSizes();
        for(int i=0; i< pictureSizes.size(); i++){
            Size size = pictureSizes.get(i);
            Log.i(TAG, "pictureSizes:width = "+ size.width
                    +" height = " + size.height);
        }
    }

    /**
     * 在log里打印出手机支持的聚焦模式
     * @param params
     */
    public void printSupportFocusMode(Camera.Parameters params){
        List<String> focusModes = params.getSupportedFocusModes();
        for(String mode : focusModes){
            Log.i(TAG, "focusModes--" + mode);
        }
    }
}
