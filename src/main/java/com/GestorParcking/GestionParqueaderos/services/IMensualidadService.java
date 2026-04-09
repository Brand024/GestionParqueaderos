package com.GestorParcking.GestionParqueaderos.services;

import com.GestorParcking.GestionParqueaderos.models.Mensualidad;
import java.util.List;

public interface IMensualidadService {


    void registrar(Mensualidad mensualidad);

    List<Mensualidad> listarTodas();

    Mensualidad buscarPorPlaca(String placa);

    void actualizar(Mensualidad mensualidad);

    void eliminar(int idMensualidad);
}