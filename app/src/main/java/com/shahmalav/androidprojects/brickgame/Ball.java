package com.shahmalav.androidprojects.brickgame;

import android.graphics.RectF;

import java.util.Random;

/**
 * Created by shahm on 6/23/2016.
 */
public class Ball {
    RectF ball;
    float velocityX;
    float velocityY;
    float x;
    float y;
    float width = 10;
    float height = 10;

    public Ball(int sx, int sy){
        x=sx / 2;
        y=sy - 60;
        velocityX = 200;
        velocityY = -400;
        ball = new RectF(x, y, x+width, y+height);
        //ball = new RectF(x, y, x+width, y-height);
    }

    public RectF getBall(){
        return ball;
    }

    public void reverseVelocityY(){
        velocityY = -velocityY;
    }

    public void reverseVelocityX(){
        velocityX = -velocityX;
    }

    public void setRandomVelocity(){
        Random gen = new Random();
        int r = gen.nextInt(2);

        if(r == 0){
            reverseVelocityX();
        }
    }

    public void clearObstacleY(float y){
        ball.bottom = y;
        ball.top = y-height;
    }

    public void clearObstacleX(float x){
        ball.left = x;
        ball.right = x+width;
    }

    public void resetBall(int x, int y){
        ball.left = x/2;
        ball.top = y-100;
        ball.right = this.x+width;
        ball.bottom = this.y+height;

    }

    public void update(long fps){
        ball.left = ball.left +(velocityX/fps);
        ball.top = ball.top + (velocityY/fps);
        ball.right = ball.left + width;
        ball.bottom = ball.top - height;

    }
}
