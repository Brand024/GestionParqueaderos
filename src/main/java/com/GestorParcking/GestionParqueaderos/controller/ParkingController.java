package com.GestorParcking.GestionParqueaderos.controller;

import com.GestorParcking.GestionParqueaderos.models.Ticket;
import com.GestorParcking.GestionParqueaderos.models.Vehiculo;
import com.GestorParcking.GestionParqueaderos.services.IParkingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parqueadero")
@Tag(name = "Gestión de Parqueadero", description = "Endpoints para entradas, salidas y vehículos")
public class ParkingController {

    @Autowired
    private IParkingService parkingService;

    @Operation(summary = "Registrar entrada", description = "Crea un ticket de entrada para una placa y tipo de vehículo")
    @PostMapping("/entrada")
    public ResponseEntity<Ticket> registrarEntrada(@RequestParam String placa, @RequestParam int idTipo) {
        Ticket ticket = parkingService.registrarEntrada(placa, idTipo);
        if (ticket != null) {
            return new ResponseEntity<>(ticket, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Registrar salida", description = "Calcula el cobro y libera la celda")
    @PostMapping("/salida/{placa}")
    public ResponseEntity<Ticket> registrarSalida(@PathVariable String placa) {
        Ticket ticket = parkingService.registrarSalida(placa);
        if (ticket != null) {
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/estado")
    public ResponseEntity<String> verificarEstado() {
        return new ResponseEntity<>("Servidor funcionando correctamente", HttpStatus.OK);
    }
}