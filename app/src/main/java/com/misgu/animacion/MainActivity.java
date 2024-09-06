package com.misgu.animacion;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    LottieAnimationView AnimationBeeSplash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //Asignacion de variables
        AnimationBeeSplash = (LottieAnimationView) findViewById(R.id.AnimationBeeSplash);
        AnimationBeeSplash.setAnimation(R.raw.beesplash);
        AnimationBeeSplash.playAnimation();
    }

    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(this::SplashAnimation, 5000);
    }

    private void SplashAnimation() {
        Intent i = new Intent(MainActivity.this, Login.class);
        startActivity(i);
        finish();
    }
}

