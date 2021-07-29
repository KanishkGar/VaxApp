package com.example.vaxapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private String password = "123456789";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        //permissions
        int CAMERA_PERMS = 0;
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, CAMERA_PERMS);
        }

        //buttons/textViews
        final Button loginButton = findViewById(R.id.LoginButton);
        final EditText firstName = findViewById(R.id.FirstName);
        final EditText lastName = findViewById(R.id.LastName);
        final EditText ssn = findViewById(R.id.SSN);

        //listeners
        final Toast loginToast = Toast.makeText(context, "You have entered an invalid Name/SSN combination", Toast.LENGTH_SHORT);

        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String passwordAttempt = ssn.getText().toString();
                if(passwordAttempt.length() != 9){
                    loginToast.show();
                    return;
                }
                String firstNameAttempt = firstName.getText().toString().toLowerCase();
                String lastNameAttempt = lastName.getText().toString().toLowerCase();

                String attempt = firstNameAttempt + lastNameAttempt + passwordAttempt.substring(5);
                database.child(attempt).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            //transition to app screen and clear the current EditText's
                            firstName.setText("");
                            lastName.setText("");
                            ssn.setText("");
                            Intent myIntent = new Intent(context, ModeSwitch.class);
                            //send an array with 0: FirstName 1: LastName 2: attempt
                            String[] dataArr = {firstNameAttempt, lastNameAttempt, attempt};
                            myIntent.putExtra("dataArr", dataArr);
                            startActivity(myIntent);
                        }else{
                            //tell the user that the username/password/ssn has been incorrectly entered
                            loginToast.show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }
}