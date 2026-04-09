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
    private IParkingService parkingService;  // Inyección del servicio que maneja la lógica de parqueadero

    @Operation(summary = "Registrar entrada", description = "Crea un ticket de entrada para una placa y tipo de vehículo")
    @PostMapping("/entrada")
    public ResponseEntity<Ticket> registrarEntrada(@RequestParam String placa, @RequestParam int idTipo) {
        Ticket ticket = parkingService.registrarEntrada(placa, idTipo); // Llamamos al servicio para registrar la entrada del vehículo
        if (ticket != null) {
            return new ResponseEntity<>(ticket, HttpStatus.CREATED); // Si se genera el ticket correctamente, devolvemos 201 CREATED
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Si no hay espacio disponible o falla la operación, devolvemos 400 BAD REQUEST
    }

    @Operation(summary = "Registrar salida", description = "Calcula el cobro y libera la celda")
    @PostMapping("/salida/{placa}")
    public ResponseEntity<Ticket> registrarSalida(@PathVariable String placa) {
        Ticket ticket = parkingService.registrarSalida(placa); // Llamamos al servicio para registrar la salida del vehículo
        if (ticket != null) {
            return new ResponseEntity<>(ticket, HttpStatus.OK); // Si se encuentra el ticket activo, devolvemos 200 OK con el detalle
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Si no existe ticket activo para esa placa, devolvemos 404 NOT FOUND
    }


    @GetMapping("/estado")
    public ResponseEntity<String> verificarEstado() {
        return new ResponseEntity<>("Servidor funcionando correctamente", HttpStatus.OK); // Endpoint simple para verificar que el servidor está funcionando
    }
}
