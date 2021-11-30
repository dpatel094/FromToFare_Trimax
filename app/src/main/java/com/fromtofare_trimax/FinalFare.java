package com.fromtofare_trimax;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FinalFare extends AppCompatActivity {
TextView text_view;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_fare);
        text_view = findViewById(R.id.text_view);
        Intent intent = getIntent();
        String fare = intent.getStringExtra("Fare");
        String ToStation = intent.getStringExtra("ToStation");
        String FromStation = intent.getStringExtra("FromStation");
        text_view.setText(FromStation+"  " +"To "+ ToStation +" :- "+fare);
    }
}