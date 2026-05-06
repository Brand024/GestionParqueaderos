package com.GestorParcking.GestionParqueaderos.repository.impl;

import com.GestorParcking.GestionParqueaderos.models.Mensualidad;
import java.util.ArrayList;
import java.util.List;

public class MensualidadDaoImpl {

    private final List<Mensualidad> mensualidades = new ArrayList<>();

    public MensualidadDaoImpl() {
        mensualidades.add(new Mensualidad(1, "ABC123", 300000));
        mensualidades.add(new Mensualidad(2, "XYZ789", 250000));
    }

    public List<Mensualidad> findAll() {
        return mensualidades;
    }

    public void deleteById(int id) {
        mensualidades.removeIf(m -> m.getId() == id);
    }
}

