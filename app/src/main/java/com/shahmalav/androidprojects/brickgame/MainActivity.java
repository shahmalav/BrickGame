package com.shahmalav.androidprojects.brickgame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends Activity {

    GameBoardView gameBoardView;
    Paddle paddle;
    Ball ball;
    Brick[] brick = new Brick[200];
    int bricks = 0;
    int sx;
    int sy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameBoardView = new GameBoardView(this);
        setContentView(gameBoardView);
    }


    class GameBoardView extends SurfaceView implements Runnable{

        SurfaceHolder surfaceHolder;
        Thread thread;
        boolean isPlaying;
        boolean isPaused = true;
        Canvas canvas;
        Paint paint;
        long fps;

        long ttf; //refactor


        public GameBoardView(Context context) {
            super(context);
            surfaceHolder = getHolder();
            paint = new Paint();
            Display display = getWindowManager().getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            sx = point.x;
            sy = point.y;
            paddle = new Paddle(sx, sy);
            ball = new Ball(sx, sy);
            restartGame();
        }

        @Override
        public void run() {
            while (isPlaying){
                long startTime = System.currentTimeMillis();

                if(!isPaused){
                    update();
                }
                draw();
                ttf = System.currentTimeMillis() - startTime;
                if(ttf >= 1){
                    fps = 1000/ttf;
                }
            }
        }

        public void update(){
            paddle.update(fps);
            ball.update(fps);
        }

        public void draw(){

            if(surfaceHolder.getSurface().isValid()){
                canvas = surfaceHolder.lockCanvas();
                canvas.drawColor(Color.argb(255,26,128,182));
                paint.setColor(Color.argb(255,255,255,255));

                //draw paddle, ball, bricks, HUD
                canvas.drawRect(paddle.getPaddle(),paint);


                paint.setColor(Color.argb(255,249,129, 0));
                for (int i =0; i< bricks; i++){
                    if(brick[i].isVisible()){
                        canvas.drawRect(brick[i].getBrick(),paint);
                    }
                }

                paint.setColor(Color.WHITE);
                canvas.drawCircle(ball.getCx(),ball.getCy(),ball.getR(),paint);

                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }

        public void pause(){
            isPlaying = false;
            try{
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void resume(){
            isPlaying = true;
            thread = new Thread(this);
            thread.start();
        }

        public void restartGame(){
            int brickWidth = sx/8;
            int brickHeight = sy/10;
            bricks = 0;
          //  ball.resetBall(sx,sy);
            for(int col = 0; col<8; col++){
                for (int row = 0; row<10; row++){
                    brick[bricks] = new Brick(row, col, brickWidth, brickHeight);
                    bricks++;
                }
            }

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            switch (event.getAction() & MotionEvent.ACTION_MASK){

                case MotionEvent.ACTION_DOWN: isPaused = false;
                                                if(event.getX() > sx/2){
                                                    paddle.setDirection('R');
                                                }else {
                                                    paddle.setDirection('L');
                                                }
                                                break;

                case MotionEvent.ACTION_UP:
                                                paddle.setDirection('C');
                                                break;
            }
            return true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameBoardView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameBoardView.pause();
    }
}
