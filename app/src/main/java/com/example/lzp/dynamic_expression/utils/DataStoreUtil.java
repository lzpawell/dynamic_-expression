package com.example.lzp.dynamic_expression.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lzp on 2016/10/25.
 */

public class DataStoreUtil {
    /*gif储存成文件， animation 储存进SQLite中*/

    public static final String gifFolder = Environment.getExternalStorageDirectory().getPath() + "/dynamicExpression/gifCache/";
    public static final String animationFolder = Environment.getExternalStorageDirectory().getPath() + "/dynamicExpression/animationCache/";

    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    public static boolean saveGifs(Bitmap[] bitmaps, int[] duration, AnimationDrawable animationDrawable){
        long time = System.currentTimeMillis();
        try {
            saveAnimation(animationDrawable,dateFormat.format(new Date()) + time);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static List<File> getAnimationsFileList(){
        LinkedList<File> animationUrls = new LinkedList<>();

        File fileDir = new File(animationFolder);
        File[] fileList = fileDir.listFiles();

        for(File file : fileList){
            animationUrls.add(file);
        }

        return animationUrls;
    }

    public static List<AnimationDrawable> getAnimationsList(List<File> files){
        LinkedList<AnimationDrawable> animationList = new LinkedList<>();

        for(File file : files){
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                AnimationDrawable animationDrawable = null;
                animationDrawable = (AnimationDrawable) ois.readObject();

                ois.close();
                animationList.add(animationDrawable);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

        return animationList;
    }

    public static boolean saveAnimation(AnimationDrawable animation, String fileName) throws FileNotFoundException,IOException {
        FileOutputStream fos = new FileOutputStream(animationFolder + fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(animation);
        oos.close();
        return true;
    }

    public static boolean delete(String filePath){
        /*删除animation 和 gif*/
        String temp[] = filePath.split("/");
        String fileName = temp[temp.length - 1];

        File gifFile = new File(gifFolder + fileName);
        File animationFile = new File(animationFolder + fileName);
        if(!gifFile.exists()){
            Log.i("Data","gifFile not exists!");
            return false;
        }else{
            gifFile.delete();
        }

        if(!animationFile.exists()){
            Log.i("Data","animationFile not exists!");
            return false;
        }else{
            animationFile.delete();
            return true;
        }
    }
}
