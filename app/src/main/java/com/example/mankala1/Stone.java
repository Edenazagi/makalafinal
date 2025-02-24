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
    private int rad,c;

    public Stone(Context context, int x, int y,int rad,int c) {
        super(x, y);
        this.rad=rad;
        bitmaparr=new Bitmap[2];
        bitmaparr[0]=BitmapFactory.decodeResource(context.getResources() ,R.drawable.babypinkstone);
        bitmaparr[0]=Bitmap.createScaledBitmap(bitmaparr[0],rad*2,rad*2,true);
        bitmaparr[1]=BitmapFactory.decodeResource(context.getResources(),R.drawable.greenstone);
        bitmaparr[1]=Bitmap.createScaledBitmap(bitmaparr[1],rad*2,rad*2,true);
        this.c=c;
    }

    @Override
    public void draw(Canvas canvas) {

        canvas.drawBitmap(bitmaparr[c],x,y,null);
    }

    public void move(int steps)
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
