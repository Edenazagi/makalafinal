package com.example.mankala1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.Random;

public class Board extends View {
    private Pit [] top;
    private Pit [] bottom;
    private final int NUN_OF_PITS=7;
    private boolean firstTime = true;
    private int color;
    private int height,width;
    private boolean whoplay,firsttime2;
    private String whoplays;
    Bitmap background;
    private Context context;
    public Handler handler;
    private ThreadGame threadGame;
    private Stone stone;
    private boolean firstTimeDrawBoard = true;
    ///////////////////////////////////////

    public Board(Context context, int color) {
        super(context);
        this.context = context;
        top=new Pit[NUN_OF_PITS];
        bottom=new Pit[NUN_OF_PITS];
        whoplay=true;//true=bottom, false=top
        whoplays="player 1";
        this.color=color;
        firsttime2=true;

        handler = new Handler(
                new Handler.Callback() {
                    @Override
                    public boolean handleMessage(@NonNull Message msg) {
                        // This method will call invalidate() on its parent View
                        // which will trigger a redraw
                        if (msg.what == 0) {
                            if(stone != null)
                                stone.updateDxDy(20,20);
                            invalidate();
                        }
                        return false;
                    }
                }
        );

        threadGame = new ThreadGame();
        threadGame.start();  // runs as thread the run() method
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
        if(firstTimeDrawBoard)
        {

            int a= (int) ((height/20)/2);
            float smallrad= (int) (a*0.6);
            int x,y;
            for (int i = 0; i < 4; i++) {

                for (int j = 0; j < 6; j++)
                {
                    if(i==0){
                        x= top[j].getX();
                        y=top[j].getY()+a;
                        Stone s=new Stone(context, x,y, (int) smallrad,0);
                        top[i].addtolist(s);
                    }
                    if(i==1)
                    {
                        x= top[j].getX()-a;
                        y=top[j].getY();
                        Stone s=new Stone(context, x,y, (int) smallrad,0);
                        top[i].addtolist(s);
                    }
                    if(i==2)
                    {
                        x= top[j].getX()+a;
                        y=top[j].getY();
                        Stone s=new Stone(context, x,y, (int) smallrad,0);
                        top[i].addtolist(s);
                    }
                    if(i==3)
                    {
                        x= top[j].getX();
                        y=top[j].getY()-a;
                        Stone s=new Stone(context, x,y, (int) smallrad,0);
                        top[i].addtolist(s);
                    }
                }
            }//צייר אבנים למעלה
            for (int i = 0; i < 4; i++) {

                for (int j = 0; j < 6; j++)
                {
                    if(i==0){
                        x= bottom[j].getX();
                        y=bottom[j].getY()+a;
                        Stone s=new Stone(context, x,y, (int) smallrad,1);
                        bottom[i].addtolist(s);
                    }
                    if(i==1){
                        x= bottom[j].getX()-a;
                        y=bottom[j].getY();
                        Stone s=new Stone(context, x,y, (int) smallrad,1);
                        bottom[i].addtolist(s);
                    }
                    if(i==2){
                        x= bottom[j].getX()+a;
                        y=bottom[j].getY();
                        Stone s=new Stone(context, x,y, (int) smallrad,1);
                        bottom[i].addtolist(s);
                    }
                    if(i==3){
                        x= bottom[j].getX();
                        y=bottom[j].getY()-a;
                        Stone s=new Stone(context, x,y, (int) smallrad,1);
                        bottom[i].addtolist(s);
                    }
                }
            }//צייר אבנים למטה
            for (int i = 0; i < top.length; i++) {
                for(int j=0;j<top[i].getArrayList().size();i++){
                    top[i].getArrayList().get(j).draw(canvas);
                }
            }
            for (int i = 0; i < bottom.length; i++) {
                for(int j=0;j<bottom[i].getArrayList().size();i++){
                    bottom[i].getArrayList().get(j).draw(canvas);
                }
            }
            firstTimeDrawBoard = false;
        }



        canvas.drawBitmap(background,0,0,null);

        for (int i = 0; i < 6; i++)
        {
            top[i].draw(canvas);
        }//צייר של הגומחות העליונות
        for (int i = 0; i < 6; i++)
        {
            bottom[i].draw(canvas);
        }//צייר של הגומחות התחתונות


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

        int i=0;
        int step=1;
        if (whoplay == true){//אם המשתמש העליון משחק
            whoplays="player 1";
            for ( i = 0; i < 6; i++) {
                int x=top[i].getStoneCount();
                float y=top[i].getY();
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    if (top[i].DidUserTouchMe((int) event.getX(), (int) event.getY()))
                    {
                            // For each stone in the selected pit
                            for (int m = 0; m < top[i].getArrayList().size(); m++) {
                                Pit destinationPit = top[i+1]; // Determine based on your game logic
                                stone = top[i].getArrayList().get(m);

                                // Calculate destination position
                                int destX = destinationPit.getX();
                                int destY = destinationPit.getY();

                                // Set small random offset so stones don't stack perfectly
                                destX += (new Random().nextInt(21) - 10);
                                destY += (new Random().nextInt(21) - 10);

                                // Tell the stone to move there
                                stone.moveTo(destX, destY);
                                step++;
                            }

                            play.Turn1(top, bottom, i);
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


    private class ThreadGame extends Thread {
        public void run() {
            super.run();
            while (true) {
                try {
                    sleep(40);  // 25 FPS animation
                    // Send message to update position
                    handler.sendEmptyMessage(0);

                } catch (InterruptedException e) {
                    // Handle interruption gracefully
                    break;
                }
            }
        }
    }
}
