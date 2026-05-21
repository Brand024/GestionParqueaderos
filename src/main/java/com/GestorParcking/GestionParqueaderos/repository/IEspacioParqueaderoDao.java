package com.GestorParcking.GestionParqueaderos.repository;

import com.GestorParcking.GestionParqueaderos.models.EspacioParqueadero;
import java.util.List;

public interface IEspacioParqueaderoDao {
    List<EspacioParqueadero> listarDisponibles();
    List<EspacioParqueadero> listarTodos();  // ← NUEVO MÉTODO
    void actualizarEstado(int idEspacio, boolean estaDisponible);
    EspacioParqueadero buscarPorNumero(int numero);
}