package com.example.prestamo20;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.ArrayList;
import java.util.List;

public class ClienteConPrestamo {

    @Embedded
    private Client client;

    @Relation(entity = Prestamo.class,
    parentColumn = "id_client",
    entityColumn = "id_cliente")
    private List<Prestamo> prestamoList = new ArrayList<>();

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Prestamo> getPrestamoList() {
        return prestamoList;
    }

    public void setPrestamoList(List<Prestamo> prestamoList) {
        this.prestamoList = prestamoList;
    }
}
