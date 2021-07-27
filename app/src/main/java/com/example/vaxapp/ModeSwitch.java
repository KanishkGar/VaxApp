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
        final Button backButton = findViewById(R.id.ModeSwitchBackButton);

        //listeners
        scanningModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, Scanner.class);
                startActivity(myIntent);
            }
        });

        qrCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, ShowQR.class);//
                startActivity(myIntent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, MainActivity.class);
                startActivity(myIntent);
            }
        });
    }
}