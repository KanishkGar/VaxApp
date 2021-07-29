package com.example.vaxapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class ShowQR extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_q_r);
        Context context = getApplicationContext();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        String[] dataArr = getIntent().getStringArrayExtra("dataArr");
        final String[] codeData = {""};

        //buttons/textViews
        final Button backButton = findViewById(R.id.QRBackButton);
        final ImageView qrImage = findViewById(R.id.QRCodeImage);

        //listeners
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        database.child(dataArr[2]).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                codeData[0] = dataArr[0] + "\n" + dataArr[1] + "\n" + snapshot.child("covidVaccine").getValue().toString();
                Log.v("NOTICEME", codeData[0]);
                try {
                    Bitmap bm = encodeAsBitmap(codeData[0]);
                    if(bm != null) {
                        qrImage.setImageBitmap(bm);
                    }
                } catch (WriterException e) {  }
            }
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });



    }


    public Bitmap encodeAsBitmap(String str) throws WriterException {
        int white = 0xFFFFFFFF;
        int black = 0xFF000000;
        int WIDTH = 500;

        BitMatrix result;
        Bitmap bitmap=null;
        try
        {
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, WIDTH, WIDTH, null);

            int w = result.getWidth();
            int h = result.getHeight();
            int[] pixels = new int[w * h];
            for (int y = 0; y < h; y++) {
                int offset = y * w;
                for (int x = 0; x < w; x++) {
                    pixels[offset + x] = result.get(x, y) ? black:white;
                }
            }
            bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, WIDTH, 0, 0, w, h);
        } catch (Exception iae) {
            iae.printStackTrace();
            return null;
        }
        return bitmap;
    }
}