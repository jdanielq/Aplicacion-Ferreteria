package com.example.ferreteriaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuprincipalActivity extends AppCompatActivity {
    Button compra;
    Button cancelar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuprincipal);
        compra=(Button)findViewById(R.id.btn_comprar);
        cancelar=(Button)findViewById(R.id.btn_cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(MenuprincipalActivity.this, Logeo1Activity.class);
                startActivity(i);
                finishAffinity();
            }
        });

        compra.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent( MenuprincipalActivity.this, CompraActivity.class);
                startActivity(i);
            }
        });
    }
}
