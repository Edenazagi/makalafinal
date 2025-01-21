package com.example.mankala1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

public class CustomDialogFinish extends Dialog implements View.OnClickListener {
    ImageButton btnYes, btnNo;
    Context context;

    public CustomDialogFinish(@NonNull Context context) {
        super(context);

        setContentView(R.layout.custondialogfinish);
        this.context = context;

        this.btnYes = findViewById(R.id.btnYes);
        this.btnNo = findViewById(R.id.btnNo);
        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(btnYes == view)
        {
            dismiss(); // eliminate the dialog
            Bundle bundle = null;
            ((GameActivity)context).reset();
        }

        if(btnNo == view)
        {
            ((GameActivity)context).finish();
        }
    }
}
