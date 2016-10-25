package com.example.lzp.dynamic_expression.utils;

import android.app.Application;
import android.graphics.Bitmap;

/**
 * Created by lzp on 2016/10/25.
 */

public class MyApplication extends Application {
    private static Bitmap bitmapMaterial = null;

    public static Bitmap getBitmapMaterial() {
        return bitmapMaterial;
    }

    public static void setBitmapMaterial(Bitmap bitmapMaterial) {
        MyApplication.bitmapMaterial = bitmapMaterial;
    }
}
