package com.GestorParcking.GestionParqueaderos.controller;

import com.GestorParcking.GestionParqueaderos.entity.MensualidadEntity;
import com.GestorParcking.GestionParqueaderos.services.IMensualidadService;
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

    @GetMapping
    public ResponseEntity<List<MensualidadEntity>> listar() {
        return new ResponseEntity<>(mensualidadService.listarTodas(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> crear(@RequestBody MensualidadEntity mensualidad) {
        mensualidadService.registrar(mensualidad);
        return new ResponseEntity<>("Mensualidad creada con éxito", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@PathVariable int id, @RequestBody MensualidadEntity mensualidad) {
        mensualidad.setIdMensualidad(id);
        mensualidadService.actualizar(mensualidad);
        return new ResponseEntity<>("Datos actualizados correctamente", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        mensualidadService.eliminar(id);
        return new ResponseEntity<>("Mensualidad eliminada correctamente", HttpStatus.OK);
    }
}