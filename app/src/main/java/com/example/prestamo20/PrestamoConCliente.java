package com.example.prestamo20;

import android.arch.persistence.room.Embedded;

import java.io.Serializable;

public class PrestamoConCliente implements Serializable {

    @Embedded
    private Client client;

    @Embedded
    private Prestamo prestamo;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }
}
