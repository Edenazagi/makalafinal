package com.example.mankala1;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    public Board boardgame;
    public int color;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boardgame = new Board(this,color);
        setContentView(boardgame);
    }
    public void reset() {
        boardgame = new Board(this,color);
        setContentView(boardgame);
    }
    private void setcolor(String backgroundColor) {

        switch (backgroundColor)
        {
            case "Blue":
            {
                linearLayout.setcolor(Color.BLUE);
                break;
            }

            case "Red":
            {
                linearLayout.setcolor(Color.RED);
                break;
            }

            default:
                break;
        }
    }

}