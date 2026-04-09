package com.GestorParcking.GestionParqueaderos.services.impl;

import com.GestorParcking.GestionParqueaderos.models.Mensualidad;
import com.GestorParcking.GestionParqueaderos.repository.IMensualidadDao;
import com.GestorParcking.GestionParqueaderos.repository.impl.MensualidadDaoImpl;
import com.GestorParcking.GestionParqueaderos.services.IMensualidadService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
    // DAO encargado de la persistencia de las mensualidades
public class MensualidadServiceImpl implements IMensualidadService {

    private IMensualidadDao mensualidadDao = new MensualidadDaoImpl();

    @Override
    public void registrar(Mensualidad mensualidad) {
        mensualidadDao.registrar(mensualidad);
    }

    @Override
    public List<Mensualidad> listarTodas() {
        return mensualidadDao.listarTodas();
    }

    @Override
    public Mensualidad buscarPorPlaca(String placa) {
        return mensualidadDao.buscarPorPlaca(placa);
    }

    @Override
    public void actualizar(Mensualidad mensualidad) {
        mensualidadDao.actualizar(mensualidad);
    }

    @Override
    public void eliminar(int idMensualidad) {
        mensualidadDao.eliminar(idMensualidad);
    }
}
