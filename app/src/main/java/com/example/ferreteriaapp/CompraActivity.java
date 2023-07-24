package com.example.ferreteriaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CompraActivity extends AppCompatActivity {

    private Button calcular;
    private Button registrar;
    private Button completarCampos;
    private Button ubicacion;
    private EditText precioTotal, precioProducto, cantidadProducto, nombreProducto, descripcionProducto;
    private AdminSQLiteOpenHelper dbHelper;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);

        calcular = findViewById(R.id.btn_calcular);
        registrar = findViewById(R.id.btn_registrarcompra);
        completarCampos = findViewById(R.id.btn_completacampos);
        precioTotal = findViewById(R.id.txt_preciototal);
        precioProducto = findViewById(R.id.txt_precioprod);
        cantidadProducto = findViewById(R.id.txt_cantidadprod);
        nombreProducto = findViewById(R.id.txt_nombreprod);
        descripcionProducto = findViewById(R.id.txt_despro);
        ubicacion = findViewById(R.id.btn_ubicacion);

        dbHelper = new AdminSQLiteOpenHelper(this, "ferreteria.db", null, 1);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String precioString = precioProducto.getText().toString();
                String cantidadString = cantidadProducto.getText().toString();

                if (!precioString.isEmpty() && !cantidadString.isEmpty()) {
                    double precio = Double.parseDouble(precioString);
                    int cantidad = Integer.parseInt(cantidadString);

                    double total = precio * cantidad;
                    precioTotal.setText(String.valueOf(total));
                } else {
                    Toast.makeText(CompraActivity.this, "Ingrese el precio y la cantidad", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ubicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CompraActivity.this, UbicacionActivity.class);
                startActivity(i);

            }
        });

        completarCampos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreProducto.getText().toString();

                if (!nombre.isEmpty()) {
                    SQLiteDatabase bd = dbHelper.getReadableDatabase();
                    String[] projection = {
                            "descripcion",
                            "precio"
                    };
                    String selection = "nombre = ?";
                    String[] selectionArgs = {nombre};

                    Cursor cursor = bd.query(
                            "Herramientas",
                            projection,
                            selection,
                            selectionArgs,
                            null,
                            null,
                            null
                    );

                    if (cursor.moveToFirst()) {
                        String descripcion = cursor.getString(cursor.getColumnIndexOrThrow("descripcion"));
                        double precio = cursor.getDouble(cursor.getColumnIndexOrThrow("precio"));

                        descripcionProducto.setText(descripcion);
                        precioProducto.setText(String.valueOf(precio));
                    } else {
                        Toast.makeText(CompraActivity.this, "No se encontró el producto", Toast.LENGTH_SHORT).show();
                    }

                    cursor.close();
                    bd.close();
                } else {
                    Toast.makeText(CompraActivity.this, "Ingrese el nombre del producto", Toast.LENGTH_SHORT).show();
                }
            }
        });


        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = nombreProducto.getText().toString();
                String descripcion = descripcionProducto.getText().toString();
                String precioString = precioProducto.getText().toString();
                String cantidadString = cantidadProducto.getText().toString();
                String precioTotalString = precioTotal.getText().toString();

                if (!nombre.isEmpty() && !descripcion.isEmpty() && !precioString.isEmpty()
                        && !cantidadString.isEmpty() && !precioTotalString.isEmpty()) {

                    double precio = Double.parseDouble(precioString);
                    int cantidad = Integer.parseInt(cantidadString);
                    double precioTotal = Double.parseDouble(precioTotalString);

                    SQLiteDatabase bd = dbHelper.getWritableDatabase();
                    ContentValues valores = new ContentValues();
                    valores.put("nombre_producto", nombre);
                    valores.put("descripcion_producto", descripcion);
                    valores.put("precio_producto", precio);
                    valores.put("cantidad", cantidad);
                    valores.put("precio_total", precioTotal);

                    long resultado = bd.insert("Compra",  null, valores);
                    bd.close();

                    if (resultado != -1) {
                        Toast.makeText(CompraActivity.this, "Compra registrada correctamente", Toast.LENGTH_SHORT).show();
                        nombreProducto.setText("");
                        descripcionProducto.setText("");
                        precioProducto.setText("");
                        cantidadProducto.setText("");
                    } else {
                        Toast.makeText(CompraActivity.this, "Error al registrar la compra", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CompraActivity.this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        EditText editText = findViewById(R.id.txt_despro);
        editText.setFocusable(false);
        editText.setClickable(false);
        editText.setCursorVisible(false);

        EditText editText2 = findViewById(R.id.txt_precioprod);
        editText2.setFocusable(false);
        editText2.setClickable(false);
        editText2.setCursorVisible(false);
    }
}