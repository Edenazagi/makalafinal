package com.example.mankala1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class RegularPit extends Pit{
    private Paint p;
    private boolean isfrontsame;
    private float rad;
    private boolean istop;

    public RegularPit(int x, int y, int color, int stoneCount,boolean istop) {
        super(x, y, color, stoneCount);
        p=new Paint();
        p.setColor(color);
        this.isfrontsame = false;
        this.istop=istop;

    }

    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        float height=canvas.getWidth();
        canvas.drawCircle(x,y,height/18,p);
        this.rad=height/18;
        p.setTextSize(50);
        p.setTextAlign(Paint.Align.CENTER);
        String stonenum= String.valueOf(stoneCount);
        p.setColor(Color.BLACK);
        if(istop)
        canvas.drawText(stonenum,x,y - height/18 - 50,p);

        else canvas.drawText(stonenum,x,y + height/18 + 60,p);
        p.setColor(color);
        

    }
    public boolean DidUserTouchMe(int xt,int yt){
        if(xt>this.x-rad&&xt<this.x+rad&&yt>this.y-rad&&yt<this.y+rad)
        {
            return true;
        }
        return false;
    }






}
