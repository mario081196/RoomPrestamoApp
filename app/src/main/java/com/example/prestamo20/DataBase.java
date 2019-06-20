package com.example.prestamo20;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Client.class, Prestamo.class}, version = 1)
public abstract class DataBase extends RoomDatabase{
    public abstract ClienteDao clienteDao();
    public abstract  PrestamoDao prestamoDao();
}
