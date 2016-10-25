package com.example.lzp.dynamic_expression.utils;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;

import java.util.List;

/**
 * Created by lzp on 2016/10/25.
 */

public class MyApplication extends Application {
    private static Bitmap bitmapMaterial = null;
    private static Context context =null;
    private static List<AnimationDrawable> dynamicExpressionDrawables = null;


    public static List<AnimationDrawable> getDynamicExpressionDrawables() {
        return dynamicExpressionDrawables;
    }

    public static void setDynamicExpressionDrawables(List<AnimationDrawable> dynamicExpressionDrawables) {
        MyApplication.dynamicExpressionDrawables = dynamicExpressionDrawables;
    }

    public static Context getContext() {
        return context;
    }

    public static Bitmap getBitmapMaterial() {
        return bitmapMaterial;
    }

    public static void setBitmapMaterial(Bitmap bitmapMaterial) {
        MyApplication.bitmapMaterial = bitmapMaterial;
    }

    public MyApplication() {
        context = this.getApplicationContext();
    }
}
