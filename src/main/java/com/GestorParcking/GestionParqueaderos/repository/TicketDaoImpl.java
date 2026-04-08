package com.GestorParcking.GestionParqueaderos.repository;

import com.GestorParcking.GestionParqueaderos.models.Ticket;
import com.GestorParcking.GestionParqueaderos.util.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class TicketDaoImpl implements ITicketDao {

    @Override
    public void generarTicketEntrada(Ticket ticket) {
        // Usamos GETDATE() de SQL Server para la hora exacta de entrada
        String sql = "INSERT INTO Ticket (placa, id_espacio, hora_entrada, valor_total) VALUES (?, ?, GETDATE(), 0)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, ticket.getPlaca());
            ps.setInt(2, ticket.getId_espacio());

            ps.executeUpdate();
            System.out.println("Ticket de entrada generado.");

        } catch (SQLException e) {
            System.err.println("Error al generar ticket: " + e.getMessage());
        }
    }
    @Override
    public Ticket buscarPorPlacaActivo(String placa) {
        String sql = "SELECT * FROM Ticket WHERE placa = ? AND hora_salida IS NULL";
        Ticket ticket = null;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, placa);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ticket = new Ticket();
                ticket.setId_ticket(rs.getInt("id_ticket"));
                ticket.setPlaca(rs.getString("placa"));
                ticket.setId_espacio(rs.getInt("id_espacio"));
                ticket.setHora_entrada(rs.getString("hora_entrada"));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar ticket activo: " + e.getMessage());
        }
        return ticket;
    }


    @Override
    public void registrarSalida(Ticket ticket) {
        // 1. Buscamos la tarifa del vehículo y calculamos el total en una sola consulta
        String sql = "UPDATE T " +
                "SET T.hora_salida = GETDATE(), " +
                "    T.valor_total = DATEDIFF(MINUTE, T.hora_entrada, GETDATE()) * TV.tarifa_minuto " +
                "FROM Ticket T " +
                "JOIN Vehiculo V ON T.placa = V.placa " +
                "JOIN TipoVehiculo TV ON V.id_tipo = TV.id_tipo " +
                "WHERE T.id_ticket = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, ticket.getId_ticket());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                System.out.println("Salida registrada y aqui esta su monto por pagar");
            }
        } catch (SQLException e) {
            System.err.println("Error al registrar salida: " + e.getMessage());
        }
    }


    @Override
    public List<Ticket> listarHistorial() {
        String sql = "SELECT * FROM Ticket ORDER BY hora_entrada DESC";
        List<Ticket> lista = new ArrayList<>();

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Ticket t = new Ticket();
                t.setId_ticket(rs.getInt("id_ticket"));
                t.setPlaca(rs.getString("placa"));
                t.setId_espacio(rs.getInt("id_espacio"));
                t.setHora_entrada(rs.getString("hora_entrada"));
                t.setHora_salida(rs.getString("hora_salida"));
                t.setValor_total(rs.getFloat("valor_total"));
                lista.add(t);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar historial: " + e.getMessage());
        }
        return lista;
    }
}