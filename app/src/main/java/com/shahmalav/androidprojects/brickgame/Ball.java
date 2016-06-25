package com.shahmalav.androidprojects.brickgame;

import android.graphics.RectF;

import java.util.Random;

/**
 * Created by shahm on 6/23/2016.
 */
public class Ball {
    private float cx;
    private float cy;
    //velocity
    private float vx;
    private float vy;

    private float r;

    public Ball(int sx, int sy){
        cx=sx / 2;
        cy=sy - 60;
        vx= 200;
        vy = -400;
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

    public void reverseVX(){
        vx = - vx;
    }

    public void reverseVY(){
        vy = -vy;
    }

    public void clearObstacleY(float y){

    }

    public void clearObstacleX(float x){
        cx = x;
        cy = 0;
    }

    public void setRandomVX(){
        Random gen = new Random();
        int n = gen.nextInt(2);
        if(n == 0){
            reverseVX();
        }
    }

    public void resetBall(int sx, int sy){
        cx = sx;
        cy = sy;
        r=10;
    }

    public void update(long fps){
        cx += (vx/fps);
        cy += (vy/fps);
    }
}
