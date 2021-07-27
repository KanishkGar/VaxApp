package com.example.vaxapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;

public class ModeSwitch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_switch);
        Context context = getApplicationContext();

        //buttons/textViews
        final Button scanningModeButton = findViewById(R.id.ScanningModeButton);
        final Button qrCodeButton = findViewById(R.id.QRCodeButton);

        //listeners
        scanningModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, ModeSwitch.class);//@TODO
                startActivity(myIntent);
            }
        });

        qrCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, ModeSwitch.class);//@TODO
                startActivity(myIntent);
            }
        });
    }
}