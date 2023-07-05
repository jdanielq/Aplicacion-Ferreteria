package com.example.ferreteriaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {
    private EditText txtNombre, txtDescripcion, txtPrecio, txtStock ,txtGanancias;
    private Button btnCalcular, btnRegistrar,  btnSiguiente, btnSalir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        btnSiguiente=(Button) findViewById(R.id.btn_siguiente);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent( RegistroActivity.this, MantenimientoActivity.class);
                startActivity(i);
            }
        });

        btnSalir = (Button)findViewById(R.id.btn_salir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent( RegistroActivity.this, Logeo2Activity.class);
                startActivity(i);
                finishAffinity();
            }
        });


        txtNombre = (EditText) findViewById(R.id.et_Nombre);
        txtDescripcion = (EditText) findViewById(R.id.et_Descripcion);
        txtPrecio = (EditText) findViewById(R.id.et_Precio);
        txtStock = (EditText) findViewById(R.id.et_Stock);
        txtGanancias = (EditText) findViewById(R.id.et_Ganancias);
        btnCalcular =  (Button) findViewById(R.id.btn_Calcular);
        btnRegistrar = (Button) findViewById(R.id.btn_Registrar);
    }
    public void Consultar(View view){
        AdminSQLiteOpenHelper admin = new
                AdminSQLiteOpenHelper(this,"administrador", null, 1);
        SQLiteDatabase bdferreteria = admin.getWritableDatabase();
        String nombre = txtNombre.getText().toString();
        Cursor fila = bdferreteria.rawQuery("select descripcion, precio, stock, ganancias from Herramientas where nombre='" + nombre + "'", null);
        if(fila.moveToFirst()){
            txtDescripcion.setText(fila.getString(0));
            txtPrecio.setText(fila.getString(1));
            txtStock.setText(fila.getString(2));
            txtGanancias.setText(fila.getString(3));
        }
        else{
            Toast.makeText(RegistroActivity.this, "¡Herramienta no Existe!",Toast.LENGTH_SHORT).show();
        }
        bdferreteria.close();
    }

    public void Calcular(View view) {
        String Precio = txtPrecio.getText().toString();
        String Stock = txtStock.getText().toString();
        if (!Precio.isEmpty() && !Stock.isEmpty()) {
            double precio = Double.parseDouble(Precio);
            int stock = Integer.parseInt(Stock);
            double ganancias = precio * stock;
            txtGanancias.setText(String.valueOf(ganancias));
        } else {
            Toast.makeText(RegistroActivity.this, "Ingrese el precio y stock para calcular las ganancias", Toast.LENGTH_SHORT).show();
        }
    }

    public void Nuevo(View view){
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        txtGanancias.setText("");
    }
    public void Registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administrador", null, 1);
        SQLiteDatabase bdferreteria = admin.getWritableDatabase();
        String nombre = txtNombre.getText().toString();
        String descripcion = txtDescripcion.getText().toString();
        double precio = Double.parseDouble(txtPrecio.getText().toString());
        int stock = Integer.parseInt(txtStock.getText().toString());
        double ganancias = Double.parseDouble(txtGanancias.getText().toString());
        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre);
        registro.put("descripcion", descripcion);
        registro.put("precio",precio);
        registro.put("stock", stock);
        registro.put("ganancias", ganancias);
        bdferreteria.insert("Herramientas", null,registro);
        bdferreteria.close();
        Toast.makeText(this,"¡Registro de la Herramienta Exitoso!", Toast.LENGTH_SHORT).show();
        Nuevo(view);
    }
}