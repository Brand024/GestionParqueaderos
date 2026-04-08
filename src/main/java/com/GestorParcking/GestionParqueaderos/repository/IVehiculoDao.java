package com.GestorParcking.GestionParqueaderos.repository;

import com.GestorParcking.GestionParqueaderos.models.Vehiculo;
import java.util.List;

public interface IVehiculoDao {
    void registrar(Vehiculo vehiculo);
    Vehiculo buscarPorPlaca(String placa);
    List<Vehiculo> listarTodos();
    void actualizar(Vehiculo vehiculo);
    void eliminar(String placa);
}