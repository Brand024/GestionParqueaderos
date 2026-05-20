package com.GestorParcking.GestionParqueaderos.services.impl;

import com.GestorParcking.GestionParqueaderos.entity.MensualidadEntity;
import com.GestorParcking.GestionParqueaderos.entity.VehiculoEntity;
import com.GestorParcking.GestionParqueaderos.repository.jpa.MensualidadJpaRepository;
import com.GestorParcking.GestionParqueaderos.repository.jpa.VehiculoJpaRepository;
import com.GestorParcking.GestionParqueaderos.services.IMensualidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MensualidadServiceImpl implements IMensualidadService {

    @Autowired
    private MensualidadJpaRepository mensualidadJpaRepository;

    @Autowired
    private VehiculoJpaRepository vehiculoJpaRepository;

    @Override
    public void registrar(MensualidadEntity mensualidad) {
        mensualidadJpaRepository.save(mensualidad);
    }

    @Override
    public List<MensualidadEntity> listarTodas() {
        return mensualidadJpaRepository.findAll();
    }

    @Override
    public MensualidadEntity buscarPorPlaca(String placa) {
        VehiculoEntity vehiculo = vehiculoJpaRepository.findByPlaca(placa).orElse(null);
        if (vehiculo != null) {
            return mensualidadJpaRepository.findByVehiculo(vehiculo).orElse(null);
        }
        return null;
    }

    @Override
    public void actualizar(MensualidadEntity mensualidad) {
        mensualidadJpaRepository.save(mensualidad);
    }

    @Override
    public void eliminar(int idMensualidad) {
        mensualidadJpaRepository.deleteById(idMensualidad);
    }
}