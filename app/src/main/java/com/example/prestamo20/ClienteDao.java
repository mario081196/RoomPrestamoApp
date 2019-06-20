package com.example.prestamo20;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ClienteDao {

    @Insert
    long Insertar(Client client);

    @Delete
    void Borrar(Client client);

    @Update
    void Actualizar(Client client);

    @Transaction
    @Query("SELECT * FROM clientTB")
    List<ClienteConPrestamo> ObtenerTodo();

   @Query("SELECT * from clientTB where id_client=:id")
    Client ObtenerCLiente(int id);


}
