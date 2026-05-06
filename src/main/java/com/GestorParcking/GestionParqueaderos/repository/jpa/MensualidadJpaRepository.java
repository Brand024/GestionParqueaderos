package com.GestorParcking.GestionParqueaderos.repository.jpa;

import com.GestorParcking.GestionParqueaderos.entity.MensualidadEntity;
import com.GestorParcking.GestionParqueaderos.entity.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MensualidadJpaRepository extends JpaRepository<MensualidadEntity, Integer> {

    // Buscar mensualidad por vehículo
    Optional<MensualidadEntity> findByVehiculo(VehiculoEntity vehiculo);

    // Buscar todas las mensualidades por estado de pago
    List<MensualidadEntity> findByPagado(Boolean pagado);
}