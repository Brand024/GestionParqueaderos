package com.GestorParcking.GestionParqueaderos.controller;

import com.GestorParcking.GestionParqueaderos.entity.MensualidadEntity;
import com.GestorParcking.GestionParqueaderos.services.IMensualidadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/mensualidades")
@Tag(name = "Gestión de Mensualidades", description = "Endpoints para CRUD de mensualidades")
public class MensualidadController {

    @Autowired
    private IMensualidadService mensualidadService;

    // LISTAR todas
    @GetMapping
    @Operation(summary = "Listar todas las mensualidades")
    public ResponseEntity<List<MensualidadEntity>> listar() {
        return new ResponseEntity<>(mensualidadService.listarTodas(), HttpStatus.OK);
    }

    // BUSCAR por ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener mensualidad por ID")
    public ResponseEntity<MensualidadEntity> obtenerPorId(@PathVariable Integer id) {
        Optional<MensualidadEntity> mensualidad = Optional.ofNullable(mensualidadService.buscarPorId(id));
        return mensualidad.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // CREAR nueva mensualidad
    @PostMapping
    @Operation(summary = "Crear nueva mensualidad")
    public ResponseEntity<?> crear(@RequestBody MensualidadEntity mensualidad) {
        try {
            mensualidadService.registrar(mensualidad);
            return new ResponseEntity<>(mensualidad, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear mensualidad: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ACTUALIZAR mensualidad
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar mensualidad existente")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody MensualidadEntity mensualidad) {
        try {
            mensualidad.setIdMensualidad(id);
            mensualidadService.actualizar(mensualidad);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar mensualidad: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ELIMINAR mensualidad
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar mensualidad")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            mensualidadService.eliminar(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar mensualidad: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}