package com.GestorParcking.GestionParqueaderos.controller;

import com.GestorParcking.GestionParqueaderos.models.Mensualidad;
import com.GestorParcking.GestionParqueaderos.services.IMensualidadService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mensualidades")
public class MensualidadController {

    @Autowired
    private IMensualidadService mensualidadService;

    // 1. GET: Listar todos
    @Operation(summary = "Obtener todas las mensualidades")
    @GetMapping
    public ResponseEntity<List<Mensualidad>> listar() {
        return new ResponseEntity<>(mensualidadService.listarTodas(), HttpStatus.OK);
    }

    // 2. POST: Crear nueva
    @Operation(summary = "Registrar una nueva mensualidad")
    @PostMapping
    public ResponseEntity<String> crear(@RequestBody Mensualidad mensualidad) {
        mensualidadService.registrar(mensualidad);
        return new ResponseEntity<>("Mensualidad creada con éxito", HttpStatus.CREATED);
    }

    // 3. PUT: Actualizar datos
    @Operation(summary = "Actualizar datos de una mensualidad")
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@PathVariable int id, @RequestBody Mensualidad mensualidad) {
        mensualidad.setId_mensualidad(id);
        mensualidadService.actualizar(mensualidad); // Asegúrate de tener este método en el Service
        return new ResponseEntity<>("Datos actualizados", HttpStatus.OK);
    }

    // 4. DELETE: Eliminar
    @Operation(summary = "Eliminar o cancelar una mensualidad")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        mensualidadService.eliminar(id);
        return new ResponseEntity<>("Mensualidad eliminada correctamente", HttpStatus.OK);
    }
}
