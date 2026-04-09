package com.GestorParcking.GestionParqueaderos.services;

import com.GestorParcking.GestionParqueaderos.models.Ticket;
import com.GestorParcking.GestionParqueaderos.models.Vehiculo;

public interface IParkingService {
    // Proceso completo de entrada
    Ticket registrarEntrada(String placa, int idTipoVehiculo);

    // Proceso completo de salida
    Ticket registrarSalida(String placa);
}