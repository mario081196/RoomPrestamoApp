package com.example.prestamo20;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

import static android.arch.persistence.room.ForeignKey.CASCADE;
import static android.arch.persistence.room.ForeignKey.RESTRICT;

@Entity
public class Prestamo implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ForeignKey(entity = Client.class, parentColumns = "id", childColumns = "id_cliente",
    onDelete = CASCADE,
    onUpdate = RESTRICT)
    public int id_cliente;
    String cliente;
    String monto;
    String interes;
    String plazo;
    String fecha_inicio;
    String fecha_fin;
    String monto_pagar;
    String monto_cuota;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Prestamo(){

    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getInteres() {
        return interes;
    }

    public void setInteres(String interes) {
        this.interes = interes;
    }

    public String getPlazo() {
        return plazo;
    }

    public void setPlazo(String plazo) {
        this.plazo = plazo;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getMonto_pagar() {
        return monto_pagar;
    }

    public void setMonto_pagar(String monto_pagar) {
        this.monto_pagar = monto_pagar;
    }

    public String getMonto_cuota() {
        return monto_cuota;
    }

    public void setMonto_cuota(String monto_cuota) {
        this.monto_cuota = monto_cuota;
    }
}
