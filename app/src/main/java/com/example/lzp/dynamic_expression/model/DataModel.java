package com.example.lzp.dynamic_expression.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.lzp.dynamic_expression.R;
import com.example.lzp.dynamic_expression.utils.MyApplication;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lzp on 2016/10/25.
 */

public class DataModel {

    //静态DataModel[] 作为默认模板提供给外界使用
    private static List<DataModel> defaultDataModels;
    private static Context context;

    //静态初始化块， 初始化dataModels
    static{
        context = MyApplication.getContext();
        defaultDataModels = new LinkedList<>();

        Bitmap[] mBackgrounds = new Bitmap[3];
        mBackgrounds[0] = BitmapFactory.decodeResource(context.getResources(), R.mipmap.bg0);
        mBackgrounds[1] = BitmapFactory.decodeResource(context.getResources(), R.mipmap.bg1);
        mBackgrounds[2] = BitmapFactory.decodeResource(context.getResources(), R.mipmap.bg2);

        int[] mDurations = new int[3];
        mDurations[0] = 500;
        mDurations[1] = 500;
        mDurations[2] = 500;

        int[] mPosXs, mPosYs, mRotations;
        mPosXs = new int[3];
        mPosYs = new int[3];
        mRotations = new int[3];

        mPosXs[0] = 200;
        mPosXs[1] = 200;
        mPosXs[2] = 200;
        mPosYs[0] = 200;
        mPosYs[1] = 200;
        mPosYs[2] = 200;

        mRotations[0] = 0;
        mRotations[1] = 30;
        mRotations[2] = 60;

        defaultDataModels.add(new DataModel(mBackgrounds,mDurations,mPosXs,mPosYs,mRotations));

        //demo2
        mBackgrounds = new Bitmap[6];
        mBackgrounds[0] = BitmapFactory.decodeResource(context.getResources(),R.mipmap.demo2_1);
        mBackgrounds[1] = BitmapFactory.decodeResource(context.getResources(),R.mipmap.demo2_2);
        mBackgrounds[2] = BitmapFactory.decodeResource(context.getResources(),R.mipmap.demo2_3);
        mBackgrounds[3] = BitmapFactory.decodeResource(context.getResources(),R.mipmap.demo2_4);
        mBackgrounds[4] = BitmapFactory.decodeResource(context.getResources(),R.mipmap.demo2_5);
        mBackgrounds[5] = BitmapFactory.decodeResource(context.getResources(), R.mipmap.demo2_6);

        mDurations = new int[6];
        for(int i = 0; i < 6; i++)
            mDurations[i] = 500 + i * 200;

        mPosXs = new int[6];
        for(int i = 0; i < 6; i++)
            mPosXs[i] = 200 + i * 15;

        mPosYs = new int[6];
        for(int i = 0; i < 6; i++)
            mPosYs[i] = 200 + i * 15;

        mRotations = new int[6];
        for(int i = 0; i < 6; i++)
            mRotations[i] = 0 + i * 20;

        defaultDataModels.add(new DataModel(mBackgrounds,mDurations,mPosXs,mPosYs,mRotations));

    }

    public static List<DataModel> getDefaultDataModels() {
        return defaultDataModels;
    }

    private Bitmap[] bitmaps;
    private int[] durations;
    private int[] posXs, posYs, rotations;

    public int[] getPosXs() {
        return posXs;
    }

    public void setPosXs(int[] posXs) {
        this.posXs = posXs;
    }

    public int[] getPosYs() {
        return posYs;
    }

    public void setPosYs(int[] posYs) {
        this.posYs = posYs;
    }

    public int[] getRotations() {
        return rotations;
    }

    public void setRotations(int[] rotations) {
        this.rotations = rotations;
    }

    public Bitmap[] getBitmaps(){
        return this.bitmaps;
    }

    public int[] getDuration(){
        return this.durations;
    }


    public DataModel(Bitmap[] bitmaps, int[] duration, int[] posXs, int[] posYs, int[] rotations){
        this.bitmaps = bitmaps;
        this.durations = duration;
        this.posXs = posXs;
        this.posYs = posYs;
        this.rotations = rotations;
    }

}
