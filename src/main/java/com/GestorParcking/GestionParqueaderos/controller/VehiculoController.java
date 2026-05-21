package com.GestorParcking.GestionParqueaderos.controller;

import com.GestorParcking.GestionParqueaderos.models.Vehiculo;
import com.GestorParcking.GestionParqueaderos.repository.IVehiculoDao;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos")
@Tag(name = "Gestión de Vehículos", description = "Endpoints para CRUD de vehículos")
public class VehiculoController {

    @Autowired
    private IVehiculoDao vehiculoDao;

    @Operation(summary = "Listar todos los vehículos")
    @GetMapping
    public ResponseEntity<List<Vehiculo>> listar() {
        List<Vehiculo> vehiculos = vehiculoDao.listarTodos();
        return new ResponseEntity<>(vehiculos, HttpStatus.OK);
    }

    @Operation(summary = "Buscar vehículo por placa")
    @GetMapping("/{placa}")
    public ResponseEntity<Vehiculo> buscarPorPlaca(@PathVariable String placa) {
        Vehiculo vehiculo = vehiculoDao.buscarPorPlaca(placa);
        if (vehiculo != null) {
            return new ResponseEntity<>(vehiculo, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Registrar nuevo vehículo")
    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody Vehiculo vehiculo) {
        try {
            // Validar datos requeridos
            if (vehiculo.getPlaca() == null || vehiculo.getPlaca().trim().isEmpty()) {
                return new ResponseEntity<>("La placa es requerida", HttpStatus.BAD_REQUEST);
            }
            if (vehiculo.getModelo() == null || vehiculo.getModelo().trim().isEmpty()) {
                return new ResponseEntity<>("El modelo es requerido", HttpStatus.BAD_REQUEST);
            }
            if (vehiculo.getColor() == null || vehiculo.getColor().trim().isEmpty()) {
                return new ResponseEntity<>("El color es requerido", HttpStatus.BAD_REQUEST);
            }

            // Verificar si ya existe
            Vehiculo existente = vehiculoDao.buscarPorPlaca(vehiculo.getPlaca());
            if (existente != null) {
                return new ResponseEntity<>("Ya existe un vehículo con esta placa", HttpStatus.CONFLICT);
            }

            vehiculoDao.registrar(vehiculo);
            return new ResponseEntity<>(vehiculo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al registrar vehículo: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Actualizar vehículo existente")
    @PutMapping("/{placa}")
    public ResponseEntity<?> actualizar(@PathVariable String placa, @RequestBody Vehiculo vehiculo) {
        try {
            Vehiculo existente = vehiculoDao.buscarPorPlaca(placa);
            if (existente == null) {
                return new ResponseEntity<>("Vehículo no encontrado", HttpStatus.NOT_FOUND);
            }

            vehiculo.setPlaca(placa);
            vehiculoDao.actualizar(vehiculo);
            return new ResponseEntity<>(vehiculo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar vehículo: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Eliminar vehículo")
    @DeleteMapping("/{placa}")
    public ResponseEntity<?> eliminar(@PathVariable String placa) {
        try {
            Vehiculo existente = vehiculoDao.buscarPorPlaca(placa);
            if (existente == null) {
                return new ResponseEntity<>("Vehículo no encontrado", HttpStatus.NOT_FOUND);
            }

            vehiculoDao.eliminar(placa);
            return new ResponseEntity<>("Vehículo eliminado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar vehículo: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}