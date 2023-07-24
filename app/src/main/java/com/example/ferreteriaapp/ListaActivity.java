package com.example.ferreteriaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ListaActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCompras;
    private AdminSQLiteOpenHelper dbHelper;
    private CompraAdapter compraAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        recyclerViewCompras = findViewById(R.id.recyclerview_compras);
        dbHelper = new AdminSQLiteOpenHelper(this, "ferreteria.db", null, 1);

        mostrarRegistros();
    }

    private void mostrarRegistros() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                "nombre_producto",
                "descripcion_producto",
                "precio_producto",
                "cantidad",
                "precio_total"
        };

        Cursor cursor = db.query(
                "Compra",
                projection,
                null,
                null,
                null,
                null,
                null
        );

        compraAdapter = new CompraAdapter(cursor);
        recyclerViewCompras.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCompras.setAdapter(compraAdapter);
    }

    private class CompraAdapter extends RecyclerView.Adapter<CompraViewHolder> {

        private Cursor cursor;

        public CompraAdapter(Cursor cursor) {
            this.cursor = cursor;
        }

        @NonNull
        @Override
        public CompraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_compra, parent, false);
            return new CompraViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CompraViewHolder holder, int position) {
            cursor.moveToPosition(position);

            String nombreProducto = cursor.getString(cursor.getColumnIndexOrThrow("nombre_producto"));
            String descripcionProducto = cursor.getString(cursor.getColumnIndexOrThrow("descripcion_producto"));
            String precioProducto = cursor.getString(cursor.getColumnIndexOrThrow("precio_producto"));
            String cantidad = cursor.getString(cursor.getColumnIndexOrThrow("cantidad"));
            String precioTotal = cursor.getString(cursor.getColumnIndexOrThrow("precio_total"));
            holder.tvNombreProducto.setText(nombreProducto);
            holder.tvDescripcionProducto.setText(descripcionProducto);
            holder.tvPrecioProducto.setText(precioProducto);
            holder.tvCantidad.setText(cantidad);
            holder.tvPrecioTotal.setText(precioTotal);
        }

        @Override
        public int getItemCount() {
            return cursor != null ? cursor.getCount() : 0;
        }
    }

    private static class CompraViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombreProducto;
        TextView tvDescripcionProducto;
        TextView tvPrecioProducto;
        TextView tvCantidad;
        TextView tvPrecioTotal;

        public CompraViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombreProducto = itemView.findViewById(R.id.tv_nombre_producto);
            tvDescripcionProducto = itemView.findViewById(R.id.tv_descripcion_producto);
            tvPrecioProducto = itemView.findViewById(R.id.tv_precio_producto);
            tvCantidad = itemView.findViewById(R.id.tv_cantidad);
            tvPrecioTotal = itemView.findViewById(R.id.tv_precio_total);
        }
    }
}
