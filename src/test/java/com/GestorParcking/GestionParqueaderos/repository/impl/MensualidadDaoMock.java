package com.GestorParcking.GestionParqueaderos.repository.impl;
import com.GestorParcking.GestionParqueaderos.models.Mensualidad;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MensualidadDaoMock {
    private final List<Mensualidad> mensualidades = new ArrayList<>();

    public MensualidadDaoMock() {
        // Usamos fechas actuales como ejemplo
        Date hoy = new Date(System.currentTimeMillis());
        Date fin = new Date(System.currentTimeMillis() + (30L * 24 * 60 * 60 * 1000)); // +30 días

        mensualidades.add(new Mensualidad(1, "ABC123", hoy, fin, true));
        mensualidades.add(new Mensualidad(2, "XYZ789", hoy, fin, false));
    }

    public List<Mensualidad> findAll() {
        return mensualidades;
    }

    public void deleteById(int id) {
        mensualidades.removeIf(m -> m.getId_mensualidad() == id);
    }
}
