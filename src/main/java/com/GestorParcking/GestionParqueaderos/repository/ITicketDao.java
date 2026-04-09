package com.GestorParcking.GestionParqueaderos.repository;

import com.GestorParcking.GestionParqueaderos.models.Ticket;
import java.util.List;

//define las operaciones básicas sobre la entidad Ticket
public interface ITicketDao {
    void generarTicketEntrada(Ticket ticket);
    void registrarSalida(Ticket ticket);
    Ticket buscarPorPlacaActivo(String placa);
    List<Ticket> listarHistorial();
}
