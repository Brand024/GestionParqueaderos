package com.GestorParcking.GestionParqueaderos.services.impl;

import com.GestorParcking.GestionParqueaderos.models.*;
import com.GestorParcking.GestionParqueaderos.repository.*;
import com.GestorParcking.GestionParqueaderos.services.IParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingServiceImpl implements IParkingService {

    private final IVehiculoDao vehiculoDao;
    private final ITicketDao ticketDao;
    private final IEspacioParqueaderoDao espacioDao;
    private final IMensualidadDao mensualidadDao;

    // Inyección de dependencias por constructor
    @Autowired
    public ParkingServiceImpl(IVehiculoDao vehiculoDao, ITicketDao ticketDao,
                              IEspacioParqueaderoDao espacioDao, IMensualidadDao mensualidadDao) {
        this.vehiculoDao = vehiculoDao;
        this.ticketDao = ticketDao;
        this.espacioDao = espacioDao;
        this.mensualidadDao = mensualidadDao;
    }

    @Override
    public Ticket registrarEntrada(String placa, int idTipoVehiculo) {
        // Validar que la placa no sea nula o vacía
        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("La placa no puede estar vacía");
        }

        Vehiculo v = vehiculoDao.buscarPorPlaca(placa);
        if (v == null) {
            v = new Vehiculo();
            v.setPlaca(placa);
            v.setIdTipo(idTipoVehiculo);
            vehiculoDao.registrar(v);
        }

        // Obtener lista de espacios disponibles
        List<EspacioParqueadero> disponibles = espacioDao.listarDisponibles();
        if (disponibles.isEmpty()) {
            throw new RuntimeException("No hay espacios de parqueadero disponibles en este momento");
        }

        // Asignar el primer espacio disponible
        EspacioParqueadero espacioAsignado = disponibles.get(0);

        // Crear ticket de entrada con placa y espacio asignado
        Ticket nuevoTicket = new Ticket();
        nuevoTicket.setPlaca(placa);
        nuevoTicket.setId_espacio(espacioAsignado.getId_espacio());

        // Registrar ticket y actualizar estado del espacio a ocupado
        ticketDao.generarTicketEntrada(nuevoTicket);
        espacioDao.actualizarEstado(espacioAsignado.getId_espacio(), false);

        return nuevoTicket;
    }

    @Override
    public Ticket registrarSalida(String placa) {
        // Validar que la placa no sea nula o vacía
        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("La placa no puede estar vacía");
        }

        Ticket ticket = ticketDao.buscarPorPlacaActivo(placa);
        if (ticket == null) {
            throw new RuntimeException("No se encontró un ticket activo para la placa: " + placa);
        }

        // Verificar si el vehículo tiene mensualidad
        Mensualidad m = mensualidadDao.buscarPorPlaca(placa);

        if (m != null && m.isPagado()) {
            // Si tiene mensualidad pagada, no se cobra tarifa
            System.out.println("Vehículo con mensualidad vigente. Aplicando tarifa $0");
            ticket.setValor_total(0);
        }

        // Registrar salida y liberar espacio
        ticketDao.registrarSalida(ticket);
        espacioDao.actualizarEstado(ticket.getId_espacio(), true);

        return ticket;
    }
}