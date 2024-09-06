package com.misgu.animacion;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Objects;

public class Login extends AppCompatActivity {
    //Creacion de variables
    EditText EditTextUser,EditTextPass;
    Button ButtonLogin;
    TextView TextViewRegister;
    String strUser,strPass;
    LottieAnimationView AnimationBee1,AnimationBee2,AnimationBee3;
    String[] Credentials = new String[5];
    Users user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        //Asignacion de elementos
        EditTextUser= (EditText) findViewById(R.id.EditTextUser);
        EditTextPass= (EditText) findViewById(R.id.EditTextPass);
        ButtonLogin= (Button) findViewById(R.id.ButtonLogin);
        TextViewRegister= (TextView) findViewById(R.id.TextViewRegister);

        //Asignacion de animaciones
        AnimationBee1= (LottieAnimationView) findViewById(R.id.AnimationBee1);
        AnimationBee2= (LottieAnimationView) findViewById(R.id.AnimationBee2);
        AnimationBee3= (LottieAnimationView) findViewById(R.id.AnimationBee3);
        AnimationBee1.setAnimation(R.raw.beehome);
        AnimationBee2.setAnimation(R.raw.beehome);
        AnimationBee3.setAnimation(R.raw.beehome);

    }

    @Override
    protected void onStart() {
        super.onStart();
        user= new Users("admin","admin","Nombre","Correo","Numero");
        //Configuracion de animacion
        AnimationBee1.setMinAndMaxFrame(0,25);
        AnimationBee1.playAnimation();
        AnimationBee2.setMinAndMaxFrame(0,25);
        AnimationBee2.playAnimation();
        AnimationBee3.setMinAndMaxFrame(0,25);
        AnimationBee3.playAnimation();

        //Funcion click ingreso a registro
        TextViewRegister.setOnClickListener(view -> {
            viewRegister();
        });

        //Funcion click ingreso a home
        ButtonLogin.setOnClickListener(view -> {
            strUser=EditTextUser.getText().toString();
            strPass=EditTextPass.getText().toString();
            if(Objects.equals(strUser, user.User) && Objects.equals(strPass, user.Pass)) {
                viewHome();
            }else{
                AlertErrorLogin();
            }
        });
    }

    //Obtiene los datos del registro
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null){
            Bundle bundle = data.getExtras();
            if(bundle!=null) {
                //Asignacion de variables
                Credentials = bundle.getStringArray("key");

                user.User = Credentials[0];
                user.Pass = Credentials[1];
                user.Name = Credentials[2];
                user.Number = Credentials[3];
                user.Mail = Credentials[4];
            }
        }
    }

    //Funcion de alerta de error de login
    private void AlertErrorLogin() {
        Toast.makeText(Login.this, "Por favor ingresar un usuario valido",Toast.LENGTH_SHORT).show();
        EditTextPass.setText("");
    }

    //Funcion de inicio de sesion
    private void viewHome() {
        //Asignacion de variables
        Credentials[0]=user.User;
        Credentials[1]=user.Pass;
        Credentials[2]=user.Name;
        Credentials[3]=user.Number;
        Credentials[4]=user.Mail;

        //Limpieza de campos
        EditTextUser.setText("");
        EditTextPass.setText("");
        Intent i =new Intent(Login.this, Home.class);
        Bundle bundle = new Bundle();
        bundle.putStringArray("key", Credentials);
        i.putExtras(bundle);
        startActivity(i);
    }

    //Funcion de registro
    public void viewRegister() {
        Intent i =new Intent(Login.this, Register.class);
        startActivityForResult(i,1);
    }


}