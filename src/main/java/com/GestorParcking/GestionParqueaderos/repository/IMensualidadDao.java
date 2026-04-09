package com.GestorParcking.GestionParqueaderos.repository;

import com.GestorParcking.GestionParqueaderos.models.Mensualidad;
import java.util.List;

//define las operaciones básicas sobre la entidad Mensualidad
public interface IMensualidadDao {
    void registrar(Mensualidad mensualidad);
    List<Mensualidad> listarTodas();
    Mensualidad buscarPorPlaca(String placa);
    void eliminar(int idMensualidad);
    void actualizar(Mensualidad mensualidad);
}
