package com.GestorParcking.GestionParqueaderos.repository.impl;

import com.GestorParcking.GestionParqueaderos.models.Mensualidad;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MensualidadDaoMock {
    private final List<Mensualidad> mensualidades = new ArrayList<>();

    public MensualidadDaoMock() {
        Date hoy = new Date(System.currentTimeMillis());
        Date fin = new Date(System.currentTimeMillis() + (30L * 24 * 60 * 60 * 1000));

        // Crear mensualidades con setters
        Mensualidad m1 = new Mensualidad();
        m1.setId_mensualidad(1);
        m1.setPlaca("ABC123");
        m1.setFecha_inicio(hoy);
        m1.setFecha_fin(fin);
        m1.setValor(120000);
        m1.setPagado(true);
        mensualidades.add(m1);

        Mensualidad m2 = new Mensualidad();
        m2.setId_mensualidad(2);
        m2.setPlaca("XYZ789");
        m2.setFecha_inicio(hoy);
        m2.setFecha_fin(fin);
        m2.setValor(80000);
        m2.setPagado(false);
        mensualidades.add(m2);
    }

    public List<Mensualidad> findAll() {
        return mensualidades;
    }

    public void deleteById(int id) {
        mensualidades.removeIf(m -> m.getId_mensualidad() == id);
    }
}