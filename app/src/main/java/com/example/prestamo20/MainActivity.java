package com.example.prestamo20;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    public String cadena_telefono;
    public String cadena_nombre;
    public String cadena_cedula;
    public String cadena_direccion;
    public String cadena_apellido;
    public String cadena_sexo;
    public String cadena_ocupacion;
    public Client nuevo = new Client();
    DataBase db;
    int id = 0;
    int posicion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db= Room.databaseBuilder(getApplicationContext(),
                DataBase.class, "Prestamo").allowMainThreadQueries().build();
        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("ID");
        posicion = bundle.getInt("posicion");
        if(id!=0){
            Actualizar(id);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_solicitud_prestamo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mn_aceptar:
                agregar();
                break;
            case R.id.mn_cancelar:
                cancelar();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void agregar(){
        EditText nombre = findViewById(R.id.editText_nombre);
        cadena_nombre = nombre.getText().toString();
        EditText telefono = findViewById(R.id.editText_telefono);
        cadena_telefono = telefono.getText().toString();
        EditText cedula = findViewById(R.id.editText_cedula);
        cadena_cedula = cedula.getText().toString();
        EditText direccion = findViewById(R.id.editText_direccion);
        cadena_direccion = direccion.getText().toString();
        EditText apellido = findViewById(R.id.editText_apellido);
        cadena_apellido = apellido.getText().toString();
        EditText ocupacion = findViewById(R.id.editText_ocupacion);
        cadena_ocupacion = ocupacion.getText().toString();
        Spinner sexo = findViewById(R.id.spinner_sexo);
        cadena_sexo = sexo.getSelectedItem().toString();
        if(cadena_nombre.isEmpty()){
            nombre.setError("Debe llenar este campo");
            Toast.makeText(this, "Debe llenar ciertos campos", Toast.LENGTH_SHORT).show();
        }
        else if(cadena_telefono.isEmpty()){
            telefono.setError("Debe llenar este campo");
            Toast.makeText(this, "Debe llenar ciertos campos", Toast.LENGTH_SHORT).show();
        }
        else if(cadena_cedula.isEmpty()){
            cedula.setError("Debe llenar este campo");
            Toast.makeText(this, "Debe llenar ciertos campos", Toast.LENGTH_SHORT).show();
        }
        else if(cadena_direccion.isEmpty()){
            direccion.setError("Debe llenar este campo");
            Toast.makeText(this, "Debe llenar ciertos campos", Toast.LENGTH_SHORT).show();
        }
        else{
            nuevo.nombre = cadena_nombre;
            nuevo.telefono = cadena_telefono;
            nuevo.cedula = cadena_cedula;
            nuevo.direccion = cadena_direccion;
            nuevo.sexo = cadena_sexo;
            nuevo.ocupacion = cadena_ocupacion;
            nuevo.apellido = cadena_apellido;
            if(id!=0)
                nuevo.setId_client(id);
            Intent intent = new Intent();
            intent.putExtra("posicion", posicion);
            intent.putExtra("cliente", (Serializable) nuevo);
            setResult(RESULT_OK, intent);
            finish();
        }

    }


    public void cancelar(){ //Si el usuario da clic en cancelar
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent); //Le mandamos a decir que el usuario dio clic en cancelar a la activity que nos invoco
        finish(); //Cerramos la activity
    }

    public void Actualizar(int id){
        Client editar = new Client();
        editar = db.clienteDao().ObtenerCLiente(id);
        EditText nombre = findViewById(R.id.editText_nombre);
        nombre.setText(editar.getNombre());
        EditText telefono = findViewById(R.id.editText_telefono);
        telefono.setText(editar.getTelefono());
        EditText cedula = findViewById(R.id.editText_cedula);
        cedula.setText(editar.getCedula());
        EditText direccion = findViewById(R.id.editText_direccion);
        direccion.setText(editar.getDireccion());
        EditText apellido = findViewById(R.id.editText_apellido);
        apellido.setText(editar.getApellido());
        EditText ocupacion = findViewById(R.id.editText_ocupacion);
        ocupacion.setText(editar.getOcupacion());
        Spinner sexo = findViewById(R.id.spinner_sexo);
        if(editar.getSexo() == "Femenino"){
            sexo.setSelection(0);
        }
        else{
            sexo.setSelection(1);
        }
    }

}
