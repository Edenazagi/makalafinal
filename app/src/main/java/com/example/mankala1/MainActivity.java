package com.example.mankala1;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton btnplay, btninstructions, btnsettings;
    private String backgroundColor = "Blue";
    private LinearLayout linearLayout;


    private ActivityResultLauncher<Intent> activityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        //linearLayout=findViewById(R.)
        init();

    }
    private void init()
    {
        btnplay=findViewById(R.id.playbtn);
        btnplay.setOnClickListener(this);
        btnsettings=findViewById(R.id.settingsbtn);
        btnsettings.setOnClickListener(this);
        btninstructions=findViewById(R.id.instructionsbtn);
        btninstructions.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==btninstructions){
            Intent intent = new Intent(this,Instructions.class);

            startActivity(intent);
        }
        if (v==btnsettings){
            Intent i = new Intent(this, Settings.class);
            activityResultLauncher.launch(i);
        }
        if (v==btnplay){
            Intent i = new Intent(this, GameActivity.class);
            i.putExtra("color", backgroundColor);
            startActivity(i);
        }
    }
    public void setcolor(String str) {
        backgroundColor = str;
        switch (str)
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

            case "Pink":
            {
                linearLayout.setcolor(Color.argb(255,255,192,203));
                //linearLayout.setcolor(0xFFFFC0CB);
                break;
            }

            default:
                break;
        }
    }
}