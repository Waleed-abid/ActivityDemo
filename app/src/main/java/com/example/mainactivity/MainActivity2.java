package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView recieveName, recieveNumber, genderTv,agetv;
        ImageView imageView2;

        recieveName = findViewById(R.id.recieveName);
        recieveNumber = findViewById(R.id.recieveNumber);
        genderTv = findViewById(R.id.gendertext);
        agetv = findViewById(R.id.agetv);
        imageView2 = findViewById(R.id.imageView2);
        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray("picture");

        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        ImageView image = (ImageView) findViewById(R.id.imageView2);

        image.setImageBitmap(bmp);


        String name = getIntent().getStringExtra("Name");
        recieveName.setText("Name: " +name);
        String Number = getIntent().getStringExtra("Number");
        recieveNumber.setText("Number: " +Number);
        String gender = getIntent().getStringExtra("Gender");
        genderTv.setText("Gender: " + gender);
        String age = getIntent().getStringExtra("age");
        agetv.setText("Age: " +age);

    }
}