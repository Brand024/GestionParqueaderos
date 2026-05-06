package com.GestorParcking.GestionParqueaderos.repository.jpa;

import com.GestorParcking.GestionParqueaderos.entity.TipoVehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoVehidculoJpaRepository extends JpaRepository<TipoVehiculoEntity, Integer> {
    // Métodos adicionales si los necesitas
}
