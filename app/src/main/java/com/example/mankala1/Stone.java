package com.example.mankala1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import java.util.Random;

public class Stone extends Position{
    private int dx,dy;
    private Bitmap []bitmaparr;

    private int n;
    private int rad;

    // Target position for animation
    private int targetX, targetY;
    private boolean isMoving = false;
    private int moveSpeed = 5; // Speed of movement

    public Stone(Context context, int x, int y, int rad, int i) {
        super(x, y);
        this.rad = rad;
        bitmaparr = new Bitmap[9];
        bitmaparr[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.green2stone);
        bitmaparr[0] = Bitmap.createScaledBitmap(bitmaparr[0], rad*2, rad*2, true);
        bitmaparr[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.pinkstone);
        bitmaparr[1] = Bitmap.createScaledBitmap(bitmaparr[1], rad*2, rad*2, true);
        this.n = i;

        // Initialize target position to current position
        targetX = x;
        targetY = y;



    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmaparr[n], x, y, null);
    }

    // Method to start moving to a new position
    public void moveTo(int newX, int newY) {
        targetX = newX;
        targetY = newY;
        isMoving = true;

        // Calculate movement direction
        updateDxDy(calculateDx(targetX), calculateDy(targetY));
    }

    // Update position based on current movement
    private void updatePosition() {
        if (isMoving) {
            // Calculate if we've reached or passed the target
            boolean reachedX = (dx > 0 && x >= targetX) || (dx < 0 && x <= targetX) || dx == 0;
            boolean reachedY = (dy > 0 && y >= targetY) || (dy < 0 && y <= targetY) || dy == 0;

            if (reachedX && reachedY) {
                // We've reached the target, stop moving
                x = targetX;
                y = targetY;
                isMoving = false;
            } else {
                // Continue moving
                x += dx;
                y += dy;

            }

        }
    }

    // Calculate dx based on target position
    private int calculateDx(int targetX) {
        int distance = targetX - x;
        if (Math.abs(distance) < moveSpeed) return distance;
        return distance > 0 ? moveSpeed : -moveSpeed;
    }

    // Calculate dy based on target position
    private int calculateDy(int targetY) {
        int distance = targetY - y;
        if (Math.abs(distance) < moveSpeed) return distance;
        return distance > 0 ? moveSpeed : -moveSpeed;
    }

    public void updateDxDy(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }


}