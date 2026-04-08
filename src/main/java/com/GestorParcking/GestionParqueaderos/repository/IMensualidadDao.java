package com.GestorParcking.GestionParqueaderos.repository;

import com.GestorParcking.GestionParqueaderos.models.Mensualidad;
import java.util.List;

public interface IMensualidadDao {
    void registrar(Mensualidad mensualidad);
    List<Mensualidad> listarTodas();
    Mensualidad buscarPorPlaca(String placa);
    void eliminar(int idMensualidad);
}