package com.GestorParcking.GestionParqueaderos.repository;

import com.GestorParcking.GestionParqueaderos.models.TipoVehiculo;
import java.util.List;

public interface ITipoVehiculoDao {
    List<TipoVehiculo> listarTipos();
    TipoVehiculo buscarPorId(int idTipo);
}