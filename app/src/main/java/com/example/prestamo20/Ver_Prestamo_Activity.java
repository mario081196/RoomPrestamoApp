package com.example.prestamo20;

import android.arch.persistence.room.Room;
import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Ver_Prestamo_Activity extends AppCompatActivity {
    public List<PrestamoConCliente> lista_prestamo = new ArrayList<>();
    public Adaptador_Prestamo adapter;
    public ListView lv;
    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver__prestamo_);
        db= Room.databaseBuilder(getApplicationContext(),
                DataBase.class, "Prestamo").allowMainThreadQueries().build();
        lv = findViewById(R.id.lv);
        lista_prestamo.addAll(db.prestamoDao().ObtenerTodo());
        adapter = new Adaptador_Prestamo(lista_prestamo, this);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PrestamoConCliente prestamoConCliente = lista_prestamo.get(position);
                Intent intent = new Intent(Ver_Prestamo_Activity.this, PagosActivity.class);
                intent.putExtra("ID", prestamoConCliente);
                startActivityForResult(intent, 1111);
            }
        });
    }
}
