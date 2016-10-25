package com.example.lzp.dynamic_expression.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import com.example.lzp.dynamic_expression.model.DataModel;

/**
 * Created by lzp on 2016/10/25.
 */

public class AnimationFactory {
    /*图片合成工具函数， 用来合成背景和前景 并且前景可旋转*/
    public static Bitmap compositePhoto(Bitmap background, Bitmap photoToBeAdd, float posX, float posY, int rotation){
        Bitmap res = Bitmap.createBitmap(background.getWidth(), background.getHeight(), background.getConfig());
        Canvas canvas = new Canvas(res);
        //准备一支画笔并设置他的参数。
        Paint paint = new Paint();

        Matrix matrix = new Matrix();
        int offsetX = photoToBeAdd.getWidth() / 2;
        int offsetY = photoToBeAdd.getHeight() / 2;
        matrix.postTranslate(-offsetX, -offsetY);
        matrix.postRotate(rotation);
        matrix.postTranslate(posX + offsetX, posY + offsetY);
        canvas.drawBitmap(background,new Matrix(),paint);
        canvas.drawBitmap(photoToBeAdd, matrix, paint);
        return res;
    }

    /*android animation 生成函数*/
    private static AnimationDrawable compositeDynamivPhoto(Bitmap[] bitmaps, int[] time) {
        AnimationDrawable frameAnim = new AnimationDrawable();
        for(int i = 0;i<bitmaps.length;i++){
            frameAnim.addFrame(new BitmapDrawable(bitmaps[i]),time[i]);
        }

        return frameAnim;
    }


    /*根据动态表情模板数组生成一组android animationDrawable*/
    public static AnimationDrawable[] createDynamicPhoto(DataModel[] dataModels, Bitmap toBeAdd){
        toBeAdd = Bitmap.createScaledBitmap(toBeAdd,200,200,true);
        AnimationDrawable[] animationDrawables = new AnimationDrawable[dataModels.length];
        int cnt = 0;
        for(DataModel model : dataModels){
            int[] posXs = model.getPosXs(),
                    posYs = model.getPosYs(),
                    durations = model.getDuration(),
                    rotations = model.getRotations();

            Bitmap[] bitmaps = model.getBitmaps();
            Bitmap[] newBitmaps = new Bitmap[bitmaps.length];

            for (int i = 0; i < bitmaps.length; i++){
                bitmaps[i] = Bitmap.createScaledBitmap(bitmaps[i],800,800,true);
                newBitmaps[i] = compositePhoto(bitmaps[i],toBeAdd,posXs[i],posYs[i],rotations[i]);
            }

            animationDrawables[cnt++] = compositeDynamivPhoto(newBitmaps,durations);

        }

        if(animationDrawables == null)
            Log.i("TAG","null");

        return animationDrawables;
    }

}
