
package com.example.mankala1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.activity.result.contract.ActivityResultContracts;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

public class Settings extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{
    private Spinner spinner;
    private String[] arrcolor = {"Select Color", "Red", "Blue", "Pink", "Yellow"};
    private boolean isFirstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item,arrcolor);
        /*((ArrayAdapter<?>)*/ aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter((SpinnerAdapter) aa);
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(this, "onSelected", Toast.LENGTH_SHORT).show();
        if(isFirstTime == false)
        {
            Intent intent = new Intent();
            intent.putExtra("color",arrcolor[position]);
            setResult(RESULT_OK, intent);
            finish();
        }
        isFirstTime = false;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Nothing", Toast.LENGTH_SHORT).show();

    }
}