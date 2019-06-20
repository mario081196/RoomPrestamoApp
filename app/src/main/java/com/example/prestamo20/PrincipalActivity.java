package com.example.prestamo20;

import android.arch.persistence.room.Room;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PrincipalActivity extends AppCompatActivity {

    public DataBase db;
    public static List<ClienteConPrestamo> lista_clientes = new ArrayList<>();
    public static List<PrestamoConCliente> lista_prestamo = new ArrayList<>();
    public static TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        tv = findViewById(R.id.tv_acciones);
        db= Room.databaseBuilder(getApplicationContext(),
                DataBase.class, "Prestamo").allowMainThreadQueries().build();
        lista_clientes.addAll(db.clienteDao().ObtenerTodo());
        lista_prestamo.addAll(db.prestamoDao().ObtenerTodo());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_icon_nuevo:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("ID",0);
                startActivityForResult(intent, 1234);
                break;
            case R.id.mn_acerca_de:
                Toast.makeText(this, "Hola Mundo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mn_ver_clientes:
                if(lista_clientes.size() == 0){
                    Toast.makeText(this, "No se han ingresado clientes", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent2 = new Intent(this, Ver_Clientes_Activity.class);
                    startActivityForResult(intent2, 2222);
                }
                break;
            case R.id.mn_ver_prestamos:
                if(lista_prestamo.size() == 0){
                    Toast.makeText(this, "No se han ingresado prestamos", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent3 = new Intent(this, Ver_Prestamo_Activity.class);

                    startActivityForResult(intent3, 3333);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1234){
            if(resultCode==0){
                tv.append("Cancelo ingreso de cliente\n");
                registerForContextMenu(tv);
            }

            else{
                Client nuevo = (Client) data.getExtras().getSerializable("cliente");
                ClienteConPrestamo clienteConPrestamo = new ClienteConPrestamo();
                clienteConPrestamo.setClient(nuevo);
                lista_clientes.add(clienteConPrestamo);
                long id = db.clienteDao().Insertar(nuevo);
                nuevo.setId_client((int)id);
                tv.append("Ingreso de cliente" + " "+ nuevo.nombre + "\n");
                registerForContextMenu(tv);
            }
        }
        if(requestCode==2222){
            if(resultCode!=0){
                Prestamo nuevo = (Prestamo) data.getExtras().getSerializable("prestamo");
                PrestamoConCliente prestamoConCliente = new PrestamoConCliente();
                prestamoConCliente.setPrestamo(nuevo);
                lista_prestamo.add(prestamoConCliente);
                long id = db.prestamoDao().Insertar(nuevo);
                nuevo.setId((int)id);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.contextual, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.ctm_borrar:
                tv.setText("");
                break;
            case R.id.ctm_copiar:
                ClipboardManager clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);

                ClipData clipData = ClipData.newPlainText("Historial", tv.getText().toString());

                clipboardManager.setPrimaryClip(clipData);
                break;
        }
        return super.onContextItemSelected(item);
    }
}
