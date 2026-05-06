package com.GestorParcking.GestionParqueaderos.services.impl;

import com.GestorParcking.GestionParqueaderos.entity.VehiculoEntity;
import com.GestorParcking.GestionParqueaderos.entity.TipoVehiculoEntity;
import com.GestorParcking.GestionParqueaderos.repository.jpa.VehiculoJpaRepository;
import com.GestorParcking.GestionParqueaderos.repository.jpa.TipoVehidculoJpaRepository;
import com.GestorParcking.GestionParqueaderos.repository.IVehiculoDao; // JDBC
import com.GestorParcking.GestionParqueaderos.models.Vehiculo; // Modelo JDBC
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VehiculoServiceHybridImpl {

    // JDBC (SQL PURO)
    private final IVehiculoDao vehiculoJdbcDao;

    // JPA
    private final VehiculoJpaRepository vehiculoJpaRepository;
    private final TipoVehidculoJpaRepository tipoVehiculoJpaRepository;

    @Autowired
    public VehiculoServiceHybridImpl(IVehiculoDao vehiculoJdbcDao,
                                     VehiculoJpaRepository vehiculoJpaRepository,
                                     TipoVehidculoJpaRepository tipoVehiculoJpaRepository) {
        this.vehiculoJdbcDao = vehiculoJdbcDao;
        this.vehiculoJpaRepository = vehiculoJpaRepository;
        this.tipoVehiculoJpaRepository = tipoVehiculoJpaRepository;
    }

    // EJEMPLO 1: Buscar vehículo con JDBC (SQL PURO)
    public Vehiculo buscarConJDBC(String placa) {
        return vehiculoJdbcDao.buscarPorPlaca(placa);
    }

    // EJEMPLO 2: Buscar vehículo con JPA
    public Optional<VehiculoEntity> buscarConJPA(String placa) {
        return vehiculoJpaRepository.findByPlaca(placa);
    }

    // EJEMPLO 3: Registrar vehículo con JPA
    @Transactional
    public VehiculoEntity registrarConJPA(String placa, String modelo, String color, Integer idTipo) {
        Optional<TipoVehiculoEntity> tipoOpt = tipoVehiculoJpaRepository.findById(idTipo);

        if (tipoOpt.isEmpty()) {
            throw new RuntimeException("Tipo de vehículo no encontrado");
        }

        VehiculoEntity vehiculo = new VehiculoEntity();
        vehiculo.setPlaca(placa);
        vehiculo.setModelo(modelo);
        vehiculo.setColor(color);
        vehiculo.setTipoVehiculo(tipoOpt.get());

        return vehiculoJpaRepository.save(vehiculo);
    }

    // EJEMPLO 4: Listar todos con JPA
    public java.util.List<VehiculoEntity> listarTodosConJPA() {
        return vehiculoJpaRepository.findAll();
    }
}