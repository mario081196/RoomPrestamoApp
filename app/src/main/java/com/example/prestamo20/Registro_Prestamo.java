package com.example.prestamo20;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Registro_Prestamo extends AppCompatActivity {
    Client client;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro__prestamo);
        Bundle bundle= getIntent().getExtras();
        client = (Client) bundle.getSerializable("cliente");
        rellenar(client);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_prestamo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(this, CalcularPrestamoActivity.class);
        i.putExtra("cliente", client);
        startActivityForResult(i, 1234);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1234) {
            if (resultCode != 0) {
                Prestamo ptr = (Prestamo) data.getExtras().getSerializable("prestamo");
                Intent intent = new Intent();
                intent.putExtra("prestamo", ptr);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void rellenar(Client cl){
        TextView tvnombre = findViewById(R.id.tv_nombre);
        TextView tvapellido = findViewById(R.id.tv_apellido);
        TextView tvsexo = findViewById(R.id.tv_sexo);
        TextView tvtelefono = findViewById(R.id.tv_telefono);
        TextView tvcedula = findViewById(R.id.tv_cedula);
        TextView tvocupacion = findViewById(R.id.tv_ocupacion);
        TextView tvdireccion = findViewById(R.id.tv_direccion);
        tvnombre.setText(cl.nombre);
        tvapellido.setText(cl.apellido);
        tvcedula.setText(cl.cedula);
        tvsexo.setText(cl.sexo);
        tvdireccion.setText(cl.direccion);
        tvtelefono.setText(cl.telefono);
        tvocupacion.setText(cl.ocupacion);
    }
}
