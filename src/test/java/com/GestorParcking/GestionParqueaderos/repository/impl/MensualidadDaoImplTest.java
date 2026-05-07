package com.GestorParcking.GestionParqueaderos.repository.impl;

import com.GestorParcking.GestionParqueaderos.models.Mensualidad;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


class MensualidadDaoImplTest {

    @Test
    void listarTodas_debeRetornarListaCorrecta() {
        MensualidadDaoMock dao = new MensualidadDaoMock();
        List<Mensualidad> resultado = dao.findAll();

        assertEquals(2, resultado.size());
        assertEquals("ABC123", resultado.get(0).getPlaca());
    }

    @Test
    void eliminar_debeReducirLista() {
        MensualidadDaoMock dao = new MensualidadDaoMock();
        dao.deleteById(1);

        List<Mensualidad> resultado = dao.findAll();
        assertEquals(1, resultado.size());
        assertEquals("XYZ789", resultado.get(0).getPlaca());
    }

    @Test
    void testFindAllReturnsInitialMensualidades() {
        MensualidadDaoMock dao = new MensualidadDaoMock();
        List<Mensualidad> result = dao.findAll();
        assertEquals(2, result.size());
        assertEquals("ABC123", result.get(0).getPlaca());
    }

    @Test
    void testDeleteByIdRemovesCorrectMensualidad() {
        MensualidadDaoMock dao = new MensualidadDaoMock();
        dao.deleteById(1);
        assertEquals(1, dao.findAll().size());
        assertFalse(dao.findAll().stream().anyMatch(m -> m.getId_mensualidad() == 1));
    }

    @Test
    void testDeleteByIdWithNonExistingIdDoesNothing() {
        MensualidadDaoMock dao = new MensualidadDaoMock();
        dao.deleteById(99);
        assertEquals(2, dao.findAll().size());
    }
}
