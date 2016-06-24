package com.shahmalav.androidprojects.brickgame;

import android.graphics.RectF;

/**
 * Created by shahm on 6/23/2016.
 */
public class Brick {
    private RectF brick;
    private static final int PADDING = 1;
    private boolean isVisible;
    private int width;
    private int height;
    private int left;
    private int top;
    private int right;
    private int bottom;

    public Brick(int r, int c, int sx, int sy){
        width = sx/8;
        height = sy/10;

        left = c*width+ PADDING;
        top = r*height+ PADDING;
        right = c*width+width-PADDING;
        bottom = r*height+height-PADDING;

        brick = new RectF(left, top, right, bottom);
        isVisible = true;
    }

    public RectF getBrick(){
        return brick;
    }

    public void destroy(){
        isVisible = false;
    }

    public boolean isVisible(){
        return isVisible;
    }
}
