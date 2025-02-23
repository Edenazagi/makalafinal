package com.example.mankala1;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import java.util.Random;

public class Stone extends Position{
    private int dx,dy;
    private Bitmap []bitmaparr;
    int n;
    private int rad;

    public Stone(Context context, int x, int y,int rad) {
        super(x, y);
        //23
        this.rad=rad;
        bitmaparr=new Bitmap[9];
        bitmaparr[0]=BitmapFactory.decodeResource(context.getResources() ,R.drawable.green2stone);
        bitmaparr[0]=Bitmap.createScaledBitmap(bitmaparr[0],rad*2,rad*2,true);
        bitmaparr[1]=BitmapFactory.decodeResource(context.getResources(),R.drawable.greenstone);
        bitmaparr[1]=Bitmap.createScaledBitmap(bitmaparr[1],rad*2,rad*2,true);
        bitmaparr[2]=BitmapFactory.decodeResource(context.getResources(),R.drawable.babypinkstone);
        bitmaparr[2]=Bitmap.createScaledBitmap(bitmaparr[2],rad*2,rad*2,true);
        bitmaparr[3]=BitmapFactory.decodeResource(context.getResources(),R.drawable.purplestone);
        bitmaparr[3]=Bitmap.createScaledBitmap(bitmaparr[3],rad*2,rad*2,true);
        bitmaparr[4]=BitmapFactory.decodeResource(context.getResources(),R.drawable.orangestone);
        bitmaparr[4]=Bitmap.createScaledBitmap(bitmaparr[4],rad*2,rad*2,true);
        bitmaparr[5]=BitmapFactory.decodeResource(context.getResources(),R.drawable.tuqizstone);
        bitmaparr[5]=Bitmap.createScaledBitmap(bitmaparr[5],rad*2,rad*2,true);
        bitmaparr[6]=BitmapFactory.decodeResource(context.getResources(),R.drawable.bluestone);
        bitmaparr[6]=Bitmap.createScaledBitmap(bitmaparr[6],rad*2,rad*2,true);
        bitmaparr[7]=BitmapFactory.decodeResource(context.getResources(),R.drawable.pinkstone);
        bitmaparr[7]=Bitmap.createScaledBitmap(bitmaparr[7],rad*2,rad*2,true);
        bitmaparr[8]=BitmapFactory.decodeResource(context.getResources(),R.drawable.yellowstone);
        bitmaparr[8]=Bitmap.createScaledBitmap(bitmaparr[8],rad*2,rad*2,true);
        Random rand = new Random();
        n = rand.nextInt(8);

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmaparr[n],x,y,null);
    }

    public void move()
    {
        x = x + dx;
        y = y + dy;
    }

    public void updateDxDy(int dx, int dy)
    {
        this.dx = dx;
        this.dy = dy;
    }

}
