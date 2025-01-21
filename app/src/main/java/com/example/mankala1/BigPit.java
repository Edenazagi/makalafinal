package com.example.mankala1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class BigPit extends Pit{
    private boolean isone;
    private Paint p;

    public BigPit(int x, int y, int color, int stoneCount, boolean isone) {
        super(x, y, color, stoneCount);
        this.isone = isone;
        p = new Paint();
        p.setColor(Color.YELLOW);
    }
    public void draw(Canvas canvas)
    {
        super.draw(canvas);

        float xf = (float) this.x;
        float yf = (float) this.y;
        float h=canvas.getHeight()/8;
        float m= (float) (canvas.getWidth()/6);
        RectF rect=new RectF(xf-h,yf+m,xf+h,yf-m);
        canvas.drawOval(rect,p);
        Paint p1=new Paint();
        p1.setColor(Color.BLACK);
        p1.setTextSize(80);
        p1.setFakeBoldText(true);
        String stonenum= String.valueOf(stoneCount);
        canvas.drawText(stonenum, x, y,p1);
    }

    @Override
    public boolean DidUserTouchMe(int xt, int yt) {
        return false;
    }


}
