package com.example.vaxapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String password = "1234567890";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();

        //buttons/textViews
        final Button loginButton = findViewById(R.id.LoginButton);
        final TextView firstName = findViewById(R.id.FirstName);
        final TextView lastName = findViewById(R.id.LastName);
        final TextView ssn = findViewById(R.id.SSN);

        //listeners
        final Toast loginToast = Toast.makeText(context, "You have entered an invalid Name/SSN combination", Toast.LENGTH_SHORT);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String passwordAttempt = ssn.getText().toString();
                if(passwordAttempt.equals(password)){
                    //transition to app screen
                }else{
                    //tell the user that the username/password/ssn has been incorrectly entered
                    loginToast.show();
                }
            }
        });

    }
}