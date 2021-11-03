package com.example.mainactivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int CAMERA_REQUEST_CODE = 102;
    private static final int CAMERA_PERM_CODE = 101;
    String choice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner;
        EditText edtName, edtNumber;
        Switch switch1;
        ImageView imageView;
        Bitmap bitmap;
        Button btnSend;
        edtName = findViewById(R.id.edtName);
        edtNumber = findViewById(R.id.edtNumber);
        switch1 = findViewById(R.id.switch1);
        btnSend = findViewById(R.id.btnSend);
        spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Gender, R.layout.selected_item);
        adapter.setDropDownViewResource(R.layout.dropdown);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        imageView = findViewById(R.id.imageView);
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askCameraPermissions();
            }
        });


        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switch1.isChecked())
                    switch1.setText("Above 18");
                else
                    switch1.setText("Below 18");

            }
        });


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*
                if(bitmap != null) {
                        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 50, bytes);
                        byte[] byteArray = bytes.toByteArray();   */
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        intent.putExtra("Name", edtName.getText().toString());
                        intent.putExtra("Number", edtNumber.getText().toString());
                        intent.putExtra("Gender", choice);
                        intent.putExtra("age", switch1.getText().toString());
                        startActivity(intent);
                    }
          });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        choice = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    private void askCameraPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        } else {
            openCamera();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST_CODE){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ImageView imageView = findViewById(R.id.imageView);
            imageView.setImageBitmap(bitmap);
        }
    }

    private void openCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA_REQUEST_CODE);
    }

}