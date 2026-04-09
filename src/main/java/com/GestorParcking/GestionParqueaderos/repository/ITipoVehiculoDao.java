package com.GestorParcking.GestionParqueaderos.repository;

import com.GestorParcking.GestionParqueaderos.models.TipoVehiculo;
import java.util.List;

//define las operaciones básicas sobre la entidad TipoVehiculo
public interface ITipoVehiculoDao {
    List<TipoVehiculo> listarTipos();
    TipoVehiculo buscarPorId(int idTipo);
}
