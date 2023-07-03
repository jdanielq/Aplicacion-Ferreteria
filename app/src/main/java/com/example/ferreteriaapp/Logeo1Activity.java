package com.example.ferreteriaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Logeo1Activity extends AppCompatActivity {

    private EditText txtUsuario, txtPassword;
    private Button btnIngresar, btnBorrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logeo1);

        txtUsuario = findViewById(R.id.et_usuario);
        txtPassword = findViewById(R.id.et_password);
        btnIngresar = findViewById(R.id.btn_ingresar);
        btnBorrar = findViewById(R.id.btn_borrar);
        btnIngresar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String usuario = txtUsuario.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();

                if (isValidCredentials(usuario, password)) {
                    Toast.makeText(Logeo1Activity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                    txtUsuario.setText("");
                    txtPassword.setText("");
                    Intent i = new Intent( Logeo1Activity.this, MenuprincipalActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(Logeo1Activity.this, "Inicio de sesión fallido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidCredentials(String usuario, String password) {
        return usuario.equals("cliente1") && password.equals("123") || usuario.equals("cliente2") && password.equals("321");

    }
    public void Borrar(View view){
        txtUsuario.setText("");
        txtPassword.setText("");
    }
}