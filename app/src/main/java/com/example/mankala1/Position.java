package com.example.mankala1;

import android.graphics.Canvas;

public abstract class Position
{
    protected int x,y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract void draw(Canvas canvas);
}
