package com.example.prestamo20;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PrestamoDao {

    @Insert
    Long Insertar(Prestamo prestamo);

    @Delete
    void Borrar(Prestamo prestamo);

    @Update
    void Actualizar(Prestamo prestamo);

    @Transaction
    @Query("Select nombre, Prestamo.* from Prestamo inner join clientTB on clientTB.id_client = id_cliente")
    List<PrestamoConCliente> ObtenerTodo();

    @Query("SELECT * from Prestamo where id=:id")
    Prestamo ObtenerPrestamo(int id);

}
