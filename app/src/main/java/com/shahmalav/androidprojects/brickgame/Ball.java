package com.shahmalav.androidprojects.brickgame;

import android.graphics.RectF;

import java.util.Random;

/**
 * Created by shahm on 6/23/2016.
 */
public class Ball {
    private float cx;
    private float cy;
    private float r;

    public Ball(int sx, int sy){
        cx=sx / 2;
        cy=sy - 60;
        r=10;
    }

    public float getCx(){
        return cx;
    }

    public float getCy(){
        return cy;
    }

    public float getR(){
        return r;
    }

    public void resetBall(int sx, int sy){
        cx = sx;
        cy = sy;
        r=10;
    }

    public void update(long fps){

    }
}
