package com.example.mankala1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    public Board boardgame;
    public int color = Color.YELLOW;
    String string;
    Intent intent;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        string = intent.getStringExtra("color");

        boardgame = new Board(this,color);
        boardgame.setColor(color);  // Move this here
        setcolor(string, boardgame);

        setContentView(boardgame);
    }
    public void reset() {
        boardgame = new Board(this,color);
        setContentView(boardgame);
    }
    public void setcolor(String str, Board boardgame) {
        if (str == null) {
            color = Color.YELLOW;  // Default color
            return;
        }
        switch (str) {
            case "Yellow": {
                color = Color.YELLOW;
                break;
            }
            case "Blue": {
                color = Color.BLUE;
                break;
            }

            case "Red": {
                color = Color.RED;
                break;
            }

            case "Pink": {
                color = (Color.argb(255, 255, 192, 203));
                break;
            }

            default:
                break;
        }
            boardgame.setColor(color);

    }

}