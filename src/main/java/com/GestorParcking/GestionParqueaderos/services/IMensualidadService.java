package com.GestorParcking.GestionParqueaderos.services;

import com.GestorParcking.GestionParqueaderos.entity.MensualidadEntity;
import java.util.List;

public interface IMensualidadService {
    void registrar(MensualidadEntity mensualidad);
    List<MensualidadEntity> listarTodas();
    MensualidadEntity buscarPorPlaca(String placa);
    void actualizar(MensualidadEntity mensualidad);
    void eliminar(int idMensualidad);
}
