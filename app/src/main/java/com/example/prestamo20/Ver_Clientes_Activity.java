package com.example.prestamo20;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Ver_Clientes_Activity extends AppCompatActivity {
    RecyclerView rv;
    public DataBase db;
    public List<ClienteConPrestamo> lista_clientes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver__clientes_);
        rv = findViewById(R.id.rv);
        db= Room.databaseBuilder(getApplicationContext(),
                DataBase.class, "Prestamo").allowMainThreadQueries().build();
        lista_clientes = db.clienteDao().ObtenerTodo();
        final CalcularPrestamoActivity.RvAdapter adapter = new CalcularPrestamoActivity.RvAdapter(lista_clientes);
        adapter.setOnClickDeleteItemListener(new CalcularPrestamoActivity.RvAdapter.OnClickDeleteItemListener() {
            @Override
            public void onItemClick(final Client client, final int pos) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Ver_Clientes_Activity.this);
                builder.setMessage("Desea eliminar "+ client.getNombre() +"?");
                builder.setNegativeButton("No",null);
                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.clienteDao().Borrar(client);
                        lista_clientes.remove(pos);
                        Toast.makeText(Ver_Clientes_Activity.this, "Eliminado!",
                                Toast.LENGTH_SHORT).show();
                        adapter.notifyItemRemoved(pos);

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        adapter.setOnItemClickListener(new CalcularPrestamoActivity.RvAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Client client, int position) {
                Intent intent = new Intent(Ver_Clientes_Activity.this, Registro_Prestamo.class);
                intent.putExtra("cliente", client);
                startActivityForResult(intent, 9999);
            }
        });
        adapter.setOnClickEditItemListener(new CalcularPrestamoActivity.RvAdapter.OnClickEditItemListener() {
            @Override
            public void onItemClick(Client client, int pos) {
                Intent intent = new Intent(Ver_Clientes_Activity.this, MainActivity.class);
                intent.putExtra("ID", client.getId_client());
                intent.putExtra("posicion", pos);
                startActivityForResult(intent, 8888);
            }
        });

        GridLayoutManager llm = new GridLayoutManager(this,1);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(llm);
        rv.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==9999 && resultCode!=0){
            Prestamo nuevo = (Prestamo) data.getExtras().getSerializable("prestamo");
            Intent intent = new Intent();
            intent.putExtra("prestamo", nuevo);
            setResult(RESULT_OK, intent);
        }
        if(requestCode==8888 && resultCode!=0){
            Client nuevo = (Client) data.getExtras().getSerializable("cliente");
            Toast.makeText(this, "" + nuevo.getId_client(), Toast.LENGTH_SHORT).show();
            db.clienteDao().Actualizar(nuevo);
            int pos = data.getExtras().getInt("posicion");
            ClienteConPrestamo clienteConPrestamo = new ClienteConPrestamo();
            clienteConPrestamo.setClient(nuevo);
            lista_clientes.set(pos, clienteConPrestamo);
            rv.getAdapter().notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
