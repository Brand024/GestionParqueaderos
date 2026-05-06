package com.GestorParcking.GestionParqueaderos.repository.impl;

import com.GestorParcking.GestionParqueaderos.models.Mensualidad;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MensualidadDaoImplTest {

    @Test
    void listarTodas_debeRetornarListaCorrecta() {
        MensualidadDaoImpl dao = new MensualidadDaoImpl();
        List<Mensualidad> resultado = dao.findAll();

        assertEquals(2, resultado.size());
        assertEquals("ABC123", resultado.get(0).getPlaca());
    }

    @Test
    void eliminar_debeReducirLista() {
        MensualidadDaoImpl dao = new MensualidadDaoImpl();
        dao.deleteById(1);

        List<Mensualidad> resultado = dao.findAll();
        assertEquals(1, resultado.size());
        assertEquals("XYZ789", resultado.get(0).getPlaca());
    }
}
