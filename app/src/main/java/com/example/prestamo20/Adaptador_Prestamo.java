package com.example.prestamo20;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adaptador_Prestamo extends BaseAdapter{

    private List<PrestamoConCliente> lista_prestamo = new ArrayList<>();
    private Context context;

    public Adaptador_Prestamo(List<PrestamoConCliente> lista_prestamo, Context context) {
        this.lista_prestamo = lista_prestamo;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lista_prestamo.size();
    }

    @Override
    public Object getItem(int position) {
        return lista_prestamo.get(position);
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PrestamoConCliente ptr = (PrestamoConCliente) getItem(position);
        convertView = (View) LayoutInflater.from(context).inflate(R.layout.item_prestamo, null);
        TextView nombre = convertView.findViewById(R.id.tv_nombre);
        TextView monto = convertView.findViewById(R.id.tv_monto);
        TextView plazo = convertView.findViewById(R.id.tv_plazo);
        monto.setText(ptr.getPrestamo().getMonto());
        plazo.setText(ptr.getPrestamo().getPlazo());
        nombre.setText(lista_prestamo.get(position).getClient().getNombre());
        return convertView;
    }

}
