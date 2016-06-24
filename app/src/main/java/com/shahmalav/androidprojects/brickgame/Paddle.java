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

    private int sx;

    private float speed;
    private char direction;

    public Paddle(int sx, int sy){
        x=sx/2;
        y=sy - 40;

        height = 20;
        width = 120;
        speed = 200;
        direction = 'C';
        this.sx = sx;
        paddle = new RectF(x, y, x+width, y+height);
    }

    public RectF getPaddle(){
        return paddle;
    }

    public void setDirection(char direction){
        this.direction = direction;
    }

    public void update(long fps){
        //TODO : update paddle x,y
        switch (direction){
            case 'L':   if(x>0) {
                            x -= speed / fps;
                        }
                        break;
            case 'R':   if(x+width<sx)
                        x += speed/fps;
                        break;
        }
        paddle.left = x;
        paddle.right = x+width;

    }



}
