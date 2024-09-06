package com.misgu.animacion;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    //Creacion de variables
    TextView TextViewRegister;
    Button ButtonRegister;
    EditText EditTextName,EditTextUser,EditTextMail,EditTextNumber,EditTextPass;
    String[] Credentials = new String[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        //Asignacion de variables de usuario
        EditTextUser= (EditText) findViewById(R.id.EditTextUser);
        EditTextPass= (EditText) findViewById(R.id.EditTextPass);
        EditTextName= (EditText) findViewById(R.id.EditTextName);
        EditTextMail= (EditText) findViewById(R.id.EditTextMail);
        EditTextNumber= (EditText) findViewById(R.id.EditTextNumber);

        //Asignacion de variables de elementos
        ButtonRegister= (Button) findViewById(R.id.ButtonRegister);
        TextViewRegister= (TextView) findViewById(R.id.TextViewRegister);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Asignacion de acciones
        ButtonRegister.setOnClickListener(view -> {
            TryRegister();
        });

        TextViewRegister.setOnClickListener(view -> {
            viewLogin();
        });

    }

    //Funcion click registro
    private void TryRegister() {
        if (EditTextName.getText().toString().isEmpty() || EditTextUser.getText().toString().isEmpty() || EditTextMail.getText().toString().isEmpty() || EditTextNumber.getText().toString().isEmpty() || EditTextPass.getText().toString().isEmpty()) {
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
        }else {
            //Asignacion de variables
            Credentials[0] = EditTextUser.getText().toString();
            Credentials[1] = EditTextPass.getText().toString();
            Credentials[2] = EditTextName.getText().toString();
            Credentials[3] = EditTextNumber.getText().toString();
            Credentials[4] = EditTextMail.getText().toString();

            //Envio de datos a login
            Intent i = new Intent(Register.this, Login.class);
            Bundle bundle = new Bundle();
            bundle.putStringArray("key", Credentials);
            i.putExtras(bundle);
            setResult(RESULT_OK, i);
            finish();
        }
    }

    //Funcion click ingreso a login
    private void viewLogin() {
        Intent i = new Intent(Register.this, Login.class);
        startActivity(i);
    }
}