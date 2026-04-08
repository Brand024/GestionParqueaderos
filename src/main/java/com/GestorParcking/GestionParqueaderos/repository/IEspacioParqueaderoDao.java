package com.GestorParcking.GestionParqueaderos.repository;

import com.GestorParcking.GestionParqueaderos.models.EspacioParqueadero;
import java.util.List;

public interface IEspacioParqueaderoDao {
    List<EspacioParqueadero> listarDisponibles();
    // Cambiamos String por boolean para que coincida con el BIT de SQL
    void actualizarEstado(int idEspacio, boolean estaDisponible);
    EspacioParqueadero buscarPorNumero(int numero);
}