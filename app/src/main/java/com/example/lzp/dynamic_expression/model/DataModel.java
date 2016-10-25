package com.example.lzp.dynamic_expression.model;

import android.graphics.Bitmap;

/**
 * Created by lzp on 2016/10/25.
 */

public class DataModel {
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
