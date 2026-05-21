package com.GestorParcking.GestionParqueaderos.controller;

import com.GestorParcking.GestionParqueaderos.models.EspacioParqueadero;
import com.GestorParcking.GestionParqueaderos.models.Ticket;
import com.GestorParcking.GestionParqueaderos.repository.IEspacioParqueaderoDao;
import com.GestorParcking.GestionParqueaderos.services.IParkingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/parqueadero")
@Tag(name = "Gestión de Parqueadero", description = "Endpoints para entradas, salidas y vehículos")
public class ParkingController {

    @Autowired
    private IParkingService parkingService;

    @Autowired
    private IEspacioParqueaderoDao espacioDao;  // ← NUEVO: Inyectar el DAO de espacios

    @Operation(summary = "Registrar entrada", description = "Crea un ticket de entrada para una placa y tipo de vehículo")
    @PostMapping("/entrada")
    public ResponseEntity<?> registrarEntrada(@RequestParam String placa, @RequestParam int idTipo) {
        try {
            Ticket ticket = parkingService.registrarEntrada(placa, idTipo);
            if (ticket != null) {
                return new ResponseEntity<>(ticket, HttpStatus.CREATED);
            }
            return new ResponseEntity<>("No hay espacios disponibles", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al registrar entrada: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Registrar salida", description = "Calcula el cobro y libera la celda")
    @PostMapping("/salida/{placa}")
    public ResponseEntity<?> registrarSalida(@PathVariable String placa) {
        try {
            Ticket ticket = parkingService.registrarSalida(placa);
            if (ticket != null) {
                return new ResponseEntity<>(ticket, HttpStatus.OK);
            }
            return new ResponseEntity<>("No se encontró ticket activo para la placa: " + placa, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al registrar salida: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ✅ NUEVO ENDPOINT - Obtener estado del parqueadero (espacios disponibles/ocupados)
    @Operation(summary = "Obtener estado del parqueadero", description = "Devuelve información sobre espacios disponibles y ocupados")
    @GetMapping("/espacios")
    public ResponseEntity<?> obtenerEspaciosDisponibles() {
        try {
            List<EspacioParqueadero> disponibles = espacioDao.listarDisponibles();
            List<EspacioParqueadero> todos = espacioDao.listarTodos();
            int total = todos.size();
            int ocupados = total - disponibles.size();
            int porcentajeOcupacion = total > 0 ? (ocupados * 100 / total) : 0;

            Map<String, Object> response = new HashMap<>();
            response.put("disponibles", disponibles.size());
            response.put("ocupados", ocupados);
            response.put("total", total);
            response.put("porcentajeOcupacion", porcentajeOcupacion);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener estado del parqueadero: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/estado")
    public ResponseEntity<String> verificarEstado() {
        return new ResponseEntity<>("Servidor funcionando correctamente", HttpStatus.OK);
    }
}