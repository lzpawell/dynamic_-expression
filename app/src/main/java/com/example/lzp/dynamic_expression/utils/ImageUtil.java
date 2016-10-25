package com.example.lzp.dynamic_expression.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by lzp on 2016/10/25.
 */

public class ImageUtil {
    /**
     * 旋转bitmap
     * @param bitmap
     * @param rotateDegree
     * @return rotaBitmap
     */
    public static Bitmap getRotateBitmap(Bitmap bitmap, float rotateDegree){
        Matrix matrix = new Matrix();
        matrix.postRotate(rotateDegree);
        Bitmap rotaBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
        return rotaBitmap;
    }
}
