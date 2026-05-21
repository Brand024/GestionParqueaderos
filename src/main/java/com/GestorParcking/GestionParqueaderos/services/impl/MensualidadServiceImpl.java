package com.GestorParcking.GestionParqueaderos.services.impl;

import com.GestorParcking.GestionParqueaderos.entity.MensualidadEntity;
import com.GestorParcking.GestionParqueaderos.repository.jpa.MensualidadJpaRepository;
import com.GestorParcking.GestionParqueaderos.services.IMensualidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MensualidadServiceImpl implements IMensualidadService {

    @Autowired
    private MensualidadJpaRepository mensualidadRepository;

    @Override
    public void registrar(MensualidadEntity mensualidad) {
        mensualidadRepository.save(mensualidad);
    }

    @Override
    public List<MensualidadEntity> listarTodas() {
        return mensualidadRepository.findAll();
    }

    @Override
    public MensualidadEntity buscarPorPlaca(String placa) {
        // Implementar si es necesario
        return null;
    }

    // ✅ NUEVO MÉTODO - Buscar por ID
    @Override
    public MensualidadEntity buscarPorId(Integer id) {
        Optional<MensualidadEntity> optional = mensualidadRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public void actualizar(MensualidadEntity mensualidad) {
        mensualidadRepository.save(mensualidad);
    }

    @Override
    public void eliminar(int idMensualidad) {
        mensualidadRepository.deleteById(idMensualidad);
    }
}