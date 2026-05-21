package com.GestorParcking.GestionParqueaderos.services.impl;

import com.GestorParcking.GestionParqueaderos.models.*;
import com.GestorParcking.GestionParqueaderos.repository.*;
import com.GestorParcking.GestionParqueaderos.services.IParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ParkingServiceImpl implements IParkingService {

    private final IVehiculoDao vehiculoDao;
    private final ITicketDao ticketDao;
    private final IEspacioParqueaderoDao espacioDao;
    private final IMensualidadDao mensualidadDao;

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
        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("La placa no puede estar vacía");
        }

        // Validar si ya hay un ticket activo para esta placa
        Ticket ticketActivo = ticketDao.buscarPorPlacaActivo(placa);
        if (ticketActivo != null) {
            throw new RuntimeException("El vehículo con placa " + placa + " ya tiene un ticket activo. Debe registrar la salida primero.");
        }

        // Buscar vehículo existente
        Vehiculo v = vehiculoDao.buscarPorPlaca(placa);

        if (v == null) {
            throw new RuntimeException("El vehículo con placa " + placa + " no está registrado. Por favor, regístrelo primero en el módulo de Vehículos.");
        }

        // Obtener lista de espacios disponibles
        List<EspacioParqueadero> disponibles = espacioDao.listarDisponibles();
        if (disponibles.isEmpty()) {
            throw new RuntimeException("No hay espacios de parqueadero disponibles");
        }

        // Asignar el primer espacio disponible
        EspacioParqueadero espacioAsignado = disponibles.get(0);

        // Crear ticket de entrada
        Ticket nuevoTicket = new Ticket();
        nuevoTicket.setPlaca(placa);
        nuevoTicket.setId_espacio(espacioAsignado.getId_espacio());

        // Registrar ticket y actualizar estado del espacio
        ticketDao.generarTicketEntrada(nuevoTicket);
        espacioDao.actualizarEstado(espacioAsignado.getId_espacio(), false);

        System.out.println("✅ Entrada registrada - Placa: " + placa + ", Espacio: " + espacioAsignado.getNumero());

        return nuevoTicket;
    }

    @Override
    public Ticket registrarSalida(String placa) {
        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("La placa no puede estar vacía");
        }

        Ticket ticket = ticketDao.buscarPorPlacaActivo(placa);
        if (ticket == null) {
            throw new RuntimeException("No se encontró un ticket activo para la placa: " + placa);
        }

        // Verificar mensualidad activa y vigente
        Mensualidad m = mensualidadDao.buscarPorPlaca(placa);
        LocalDate hoy = LocalDate.now();
        boolean tieneMensualidadActiva = false;

        if (m != null && m.isPagado()) {
            LocalDate fechaInicio = m.getFecha_inicio().toLocalDate();
            LocalDate fechaFin = m.getFecha_fin().toLocalDate();

            if (!fechaInicio.isAfter(hoy) && !fechaFin.isBefore(hoy)) {
                tieneMensualidadActiva = true;
                System.out.println("✅ Vehículo con mensualidad VIGENTE y PAGADA. Aplicando tarifa $0");
            }
        }

        // ✅ Registrar salida según corresponda
        if (tieneMensualidadActiva) {
            ticketDao.registrarSalidaConMensualidad(ticket);
            ticket.setValor_total(0);
        } else {
            ticketDao.registrarSalida(ticket);
        }

        // Liberar espacio
        espacioDao.actualizarEstado(ticket.getId_espacio(), true);

        System.out.println("✅ Salida registrada - Placa: " + placa + ", Total: $" + ticket.getValor_total());

        return ticket;
    }
}