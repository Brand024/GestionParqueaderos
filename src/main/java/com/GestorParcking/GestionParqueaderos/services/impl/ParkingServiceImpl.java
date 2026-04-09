package com.GestorParcking.GestionParqueaderos.services.impl;

import com.GestorParcking.GestionParqueaderos.models.*;
import com.GestorParcking.GestionParqueaderos.repository.*;
import com.GestorParcking.GestionParqueaderos.repository.impl.*;
import com.GestorParcking.GestionParqueaderos.services.IParkingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingServiceImpl implements IParkingService {

    private IVehiculoDao vehiculoDao = new VehiculoDaoImpl();
    private ITicketDao ticketDao = new TicketDaoImpl();
    private IEspacioParqueaderoDao espacioDao = new EspacioParqueaderoImpl();
    private IMensualidadDao mensualidadDao = new MensualidadDaoImpl(); // <--- NUEVO

    @Override
    public Ticket registrarEntrada(String placa, int idTipoVehiculo) {
        Vehiculo v = vehiculoDao.buscarPorPlaca(placa);
        if (v == null) {
            v = new Vehiculo();
            v.setPlaca(placa);
            v.setIdTipo(idTipoVehiculo);
            vehiculoDao.registrar(v);
        }

        List<EspacioParqueadero> disponibles = espacioDao.listarDisponibles();
        if (disponibles.isEmpty()) {
            System.err.println("No hay espacios disponibles");
            return null;
        }

        EspacioParqueadero espacioAsignado = disponibles.get(0);

        Ticket nuevoTicket = new Ticket();
        nuevoTicket.setPlaca(placa);
        nuevoTicket.setId_espacio(espacioAsignado.getId_espacio());

        ticketDao.generarTicketEntrada(nuevoTicket);
        espacioDao.actualizarEstado(espacioAsignado.getId_espacio(), false);

        return nuevoTicket;
    }

    @Override
    public Ticket registrarSalida(String placa) {
        Ticket ticket = ticketDao.buscarPorPlacaActivo(placa);
        if (ticket != null) {
            // Verificamos si existe mensualidad para esta placa
            Mensualidad m = mensualidadDao.buscarPorPlaca(placa);

            if (m != null) {

                System.out.println("Vehículo con mensualidad. Aplicando tarifa $0");
                ticket.setValor_total(0);
            }

            ticketDao.registrarSalida(ticket);
            espacioDao.actualizarEstado(ticket.getId_espacio(), true);
            return ticket;
        }
        return null;
    }
}
