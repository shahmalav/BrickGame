package com.shahmalav.androidprojects.brickgame;

import android.graphics.RectF;

/**
 * Created by shahm on 6/23/2016.
 */
public class Paddle {
    private RectF paddle;
    private float width;
    private float height;

    private float x;
    private float y;

    private float speed;

    public Paddle(int sx, int sy){
        x=sx/2;
        y=sy - 10;
        height = 20;
        width = 120;
        speed = 200;
        paddle = new RectF(x, y, x+width, y+height);
    }

    public RectF getPaddle(){
        return paddle;
    }


    public void update(long fps){
        //TODO : update paddle x,y
    }



}
