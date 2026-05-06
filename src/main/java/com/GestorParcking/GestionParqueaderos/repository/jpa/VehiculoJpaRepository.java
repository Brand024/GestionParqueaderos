package com.GestorParcking.GestionParqueaderos.repository.jpa;

import com.GestorParcking.GestionParqueaderos.entity.VehiculoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface VehiculoJpaRepository extends JpaRepository<VehiculoEntity, String> {
    Optional<VehiculoEntity> findByPlaca(String placa);
}