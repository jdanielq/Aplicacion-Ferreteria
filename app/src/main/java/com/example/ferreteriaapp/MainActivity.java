package com.example.ferreteriaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnCliente, btnTrabajador, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTrabajador=(Button) findViewById(R.id.btn_trabajador);
        btnTrabajador.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent( MainActivity.this, Logeo2Activity.class);
                startActivity(i);
            }
        });
        btnCliente=(Button) findViewById(R.id.btn_cliente);
        btnCliente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent( MainActivity.this, Logeo1Activity.class);
                startActivity(i);
            }
        });

        btnSalir = (Button) findViewById(R.id.btn_salir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

    }
}