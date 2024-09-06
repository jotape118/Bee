package com.misgu.animacion;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {
    ImageView imageView;
    TextView TextViewName,TextViewNumber,TextViewMail;
    String[] Credentials = new String[5];
    Users user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        TextViewName = (TextView) findViewById(R.id.TextViewName);
        TextViewNumber = (TextView) findViewById(R.id.TextViewNumber);
        TextViewMail = (TextView) findViewById(R.id.TextViewMail);
        imageView=(ImageView) findViewById(R.id.ImageView);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Credentials = bundle.getStringArray("key");

        user = new Users(Credentials[0],Credentials[1],Credentials[2],Credentials[3],Credentials[4]);
        TextViewName.setText(user.Name);
        TextViewMail.setText(user.Mail);
        TextViewNumber.setText(user.Number);

        imageView.setOnClickListener(view -> {
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i,0);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap= (Bitmap) data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);
    }
}