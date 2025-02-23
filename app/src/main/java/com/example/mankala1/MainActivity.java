package com.example.mankala1;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import androidx.activity.result.contract.ActivityResultContracts;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton btnplay, btninstructions, btnsettings;
    private int backgroundColor = Color.YELLOW;
    private LinearLayout linearLayout;
    private String currentColor = "Yellow";



    private ActivityResultLauncher<Intent> activityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_opening);
        //linearLayout=findViewById(R.)
        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            String color = data.getStringExtra("color");
                            setcolor(color);
                        }
                    }
                }
        );
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
        if (v==btnplay) {
            Intent in = new Intent(this, GameActivity.class);
            in.putExtra("color", currentColor); // Pass string instead of int
            startActivity(in);
        }
    }
    public void setcolor(String str) {
        currentColor = str; // Store the color string
        switch (str) {
            case "Blue":
                backgroundColor = Color.BLUE;
                break;
            case "Red":
                backgroundColor = Color.RED;
                break;
            case "Pink":
                backgroundColor = Color.argb(255,255,192,203);
                break;
            case "Yellow":
                backgroundColor = Color.YELLOW;
                break;
        }
    }
}