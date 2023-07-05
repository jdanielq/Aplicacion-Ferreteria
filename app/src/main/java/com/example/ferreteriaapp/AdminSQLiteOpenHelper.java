package com.example.ferreteriaapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ferreteria.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_HERRAMIENTAS = "Herramientas";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_DESCRIPCION = "descripcion";
    private static final String COLUMN_PRECIO = "precio";
    private static final String COLUMN_STOCK = "stock";
    private static final String COLUMN_GANANCIAS = "ganancias";
    private static final String TABLE_COMPRA = "Compra";
    private static final String COLUMN_NOMBRE_PRODUCTO = "nombre_producto";
    private static final String COLUMN_DESCRIPCION_PRODUCTO = "descripcion_producto";
    private static final String COLUMN_PRECIO_PRODUCTO = "precio_producto";
    private static final String COLUMN_CANTIDAD = "cantidad";
    private static final String COLUMN_PRECIO_TOTAL = "precio_total";

    public AdminSQLiteOpenHelper(@Nullable Context context, String s, Object o, int i) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase bdferreteria) {
        bdferreteria.execSQL("create table Herramientas(nombre text primary key, descripcion text, precio real, stock int, ganancias real)");

        bdferreteria.execSQL("create table Compra(nombre_producto text, descripcion_producto text, precio_producto real, cantidad int, precio_total real)");

        bdferreteria.execSQL("INSERT INTO Herramientas(nombre, descripcion, precio, stock, ganancias) VALUES ('Martillo', 'Herramienta para golpear', 10.99, 50, 0.0)");
        bdferreteria.execSQL("INSERT INTO Herramientas(nombre, descripcion, precio, stock, ganancias) VALUES ('Destornillador', 'Herramienta para apretar tornillos', 5.99, 100, 0.0)");
        bdferreteria.execSQL("INSERT INTO Herramientas(nombre, descripcion, precio, stock, ganancias) VALUES ('Sierra', 'Herramienta para cortar', 15.99, 30, 0.0)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}