package com.example.mankala1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.Image;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class Board extends View {
    private Pit [] top;
    private Pit [] bottom;
    private final int NUN_OF_PITS=7;
    private boolean firstTime = true;
    private int color;
    private int height,width;
    private boolean whoplay;
    private String whoplays;
    Bitmap background;
    private Context context;

    public Board(Context context, int color) {
        super(context);
        this.context = context;
        top=new Pit[NUN_OF_PITS];
        bottom=new Pit[NUN_OF_PITS];
        whoplay=true;//true=bottom, false=top
        whoplays="player 1";
        this.color=color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        height=canvas.getWidth();
        width=canvas.getHeight();
        if(firstTime)
        {
            initBoard();
            firstTime = false;
        }
        drawBoard(canvas);
    }

    private void initBoard() {
        float radus=height/20;
        float xinitial=height/2;
        float revah=height/15;
        xinitial-=revah;
        int a=1;
        for (int i = 2; i >=0; i--) {
            top[i]=new RegularPit((int) (xinitial),width/4, this.color,4,true);
            bottom[i+a]=new RegularPit((int) (xinitial),(width/4)*3, this.color,4,false);
            xinitial=xinitial-radus-revah;
            a=a+2;
        }//יוצר גומחות ומכניס אותן למערך חצי מסך ראשון
        xinitial=height/2+revah;
        a=1;
        for (int i = 3; i <=5; i++) {
            top[i]=new RegularPit((int) (xinitial),width/4, this.color,4,true);
            bottom[i-a]=new RegularPit((int) (xinitial),(width/4)*3, this.color,4,false);
            xinitial=xinitial+radus+revah;
            a=a+2;
        }//יוצר גומחות ומכניס אותן למערך חצי מסך שני

        background= BitmapFactory.decodeResource(getResources(),R.drawable.canvasbackground);
        background = Bitmap.createScaledBitmap(background,height,width,false);

        bottom[6]=new BigPit(height/15,width/2,this.color,0,false);
        top[6]=new BigPit((int) (height*0.95),width/2,this.color,0,false);

    }

    private void drawBoard(Canvas canvas)
    {
        int a= (int) ((height/20)/2);
        float smallrad= (float) (a*0.6);
        int x,y;

        canvas.drawBitmap(background,0,0,null);

        for (int i = 0; i < 6; i++) {
            top[i].draw(canvas);
        }//צייר של הגומחות העליונות
        for (int i = 0; i < 6; i++) {
            bottom[i].draw(canvas);
        }//צייר של הגומחות התחתונות

        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 6; j++)
            {
                if(i==0){
                    x= top[j].getX();
                    y=top[j].getY()+a;
                    canvas.drawCircle((float) x,(float) y,smallrad,new Paint());
                }
                if(i==1){
                    x= top[j].getX()-a;
                    y=top[j].getY();
                    canvas.drawCircle((float) x,(float) y,smallrad,new Paint());
                }
                if(i==2){
                    x= top[j].getX()+a;
                    y=top[j].getY();
                    canvas.drawCircle((float) x,(float) y,smallrad,new Paint());
                }
                if(i==3){
                    x= top[j].getX();
                    y=top[j].getY()-a;
                    canvas.drawCircle((float) x,(float) y,smallrad,new Paint());
                }


            }
        }//צייר אבנים למעלה
        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < 6; j++)
            {
                if(i==0){
                    x= bottom[j].getX();
                    y=bottom[j].getY()+a;
                    canvas.drawCircle((float) x,(float) y,smallrad,new Paint());
                }
                if(i==1){
                    x= bottom[j].getX()-a;
                    y=bottom[j].getY();
                    canvas.drawCircle((float) x,(float) y,smallrad,new Paint());
                }
                if(i==2){
                    x= bottom[j].getX()+a;
                    y=bottom[j].getY();
                    canvas.drawCircle((float) x,(float) y,smallrad,new Paint());
                }
                if(i==3){
                    x= bottom[j].getX();
                    y=bottom[j].getY()-a;
                    canvas.drawCircle((float) x,(float) y,smallrad,new Paint());
                }


            }
        }//צייר אבנים למטה

        top[6].draw(canvas);
        bottom[6].draw(canvas);
        Paint paint=new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(100);
        paint.setFakeBoldText(true);
        canvas.drawText(whoplays,canvas.getWidth()/2,canvas.getHeight()/2,paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        GameModule play=new GameModule();

        if(event.getAction() != MotionEvent.ACTION_DOWN)
            return true;

        int a=0;
        float radus=height/20;
        int old = 0,i=0;
        if (whoplay == true){//אם המשתמש העליון משחק
            whoplays="player 1";
            for ( i = 0; i < 6; i++) {
                int x=top[i].getStoneCount();
                float y=top[i].getY();
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    if (top[i].DidUserTouchMe((int) event.getX(), (int) event.getY()))
                    {
                        play.Turn1(top,bottom,i);
                        invalidate();
                        if(i==6-x)
                        {
                            whoplay=true;
                            whoplays="player 1";
                        }
                        else
                        {
                            whoplay=false;
                            whoplays="player 2";
                        }
                        if(play.IsWon(bottom,top))
                        {
                            createDialog();
                        }
                    }
                }
            }
        }
        else  {
            for ( i = 0; i < 6; i++) {
                int x=bottom[i].getStoneCount();
                float y=bottom[i].getY();
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    if (bottom[i].DidUserTouchMe((int) event.getX(), (int) event.getY()))
                    {
                        play.Turn2(bottom,top,i);
                        invalidate();
                        if(i==6-x)
                        {
                            whoplay=false;
                            whoplays="player 2";
                        }
                        else
                        {
                            whoplay=true;
                            whoplays="player 1";
                        }
                        if(play.IsWon(bottom,top))
                        {
                            createDialog();
                        }
                    }
                }
            }
        }
        return true;
    }
    private void createDialog() {
        CustomDialogFinish customDialog = new CustomDialogFinish(context);
        customDialog.show();
    }
}
