package com.example.mankala1;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import java.util.ArrayList;

public abstract class Pit extends Position{
    protected int  color;
    protected int stoneCount;
    private Paint p;
    ArrayList<Stone> arrayList;

    public Pit(int x, int y, int color, int stoneCount) {
        super(x, y);
        arrayList = new ArrayList<>();
        this.color = color;
        this.stoneCount = stoneCount;
        p=new Paint();
        p.setColor(color);
    }
    public void addtolist(Stone stone)
    {
        arrayList.add(stone);
    }
    public int remove(){
        int k=this.stoneCount;
        this.stoneCount=0;
        return k;
    }
    public void draw(Canvas canvas) {
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i).draw(canvas);
        }
    }

    public ArrayList<Stone> getArrayList() {
        return arrayList;
    }

    public void setStoneCount(int stoneCount) {
        this.stoneCount = stoneCount;
    }

    public void addStones(int num){
        this.stoneCount=this.stoneCount+num;
    };

    public abstract boolean DidUserTouchMe(int xt, int yt);

    public int getStoneCount() {
        return stoneCount;
    }
}
