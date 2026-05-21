package com.GestorParcking.GestionParqueaderos.repository.impl;

import com.GestorParcking.GestionParqueaderos.models.Ticket;
import com.GestorParcking.GestionParqueaderos.repository.ITicketDao;
import com.GestorParcking.GestionParqueaderos.util.Conexion;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketDaoImpl implements ITicketDao {

    @Override
    public void generarTicketEntrada(Ticket ticket) {
        Ticket existente = buscarPorPlacaActivo(ticket.getPlaca());
        if (existente != null) {
            throw new RuntimeException("Ya existe un ticket activo para esta placa");
        }

        String sql = "INSERT INTO Ticket (placa, id_espacio, hora_entrada, valor_total) VALUES (?, ?, GETDATE(), 0)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, ticket.getPlaca());
            ps.setInt(2, ticket.getId_espacio());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                ticket.setId_ticket(rs.getInt(1));
            }

            System.out.println("✅ Ticket de entrada generado para: " + ticket.getPlaca());

        } catch (SQLException e) {
            System.err.println("Error al generar ticket: " + e.getMessage());
            throw new RuntimeException("Error al generar ticket: " + e.getMessage(), e);
        }
    }

    @Override
    public Ticket buscarPorPlacaActivo(String placa) {
        String sql = "SELECT id_ticket, placa, id_espacio, hora_entrada, hora_salida, valor_total FROM Ticket WHERE placa = ? AND hora_salida IS NULL";
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
                ticket.setHora_salida(rs.getString("hora_salida"));
                ticket.setValor_total(rs.getFloat("valor_total"));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar ticket activo: " + e.getMessage());
        }
        return ticket;
    }

    // ✅ Método normal - Calcula el valor (para vehículos SIN mensualidad)
    @Override
    public void registrarSalida(Ticket ticket) {
        String sql = "UPDATE T " +
                "SET T.hora_salida = GETDATE(), " +
                "    T.valor_total = DATEDIFF(MINUTE, T.hora_entrada, GETDATE()) * TV.tarifa_minuto " +
                "FROM Ticket T " +
                "INNER JOIN Vehiculo V ON T.placa = V.placa " +
                "INNER JOIN TipoVehiculo TV ON V.id_tipo = TV.id_tipo " +
                "WHERE T.id_ticket = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, ticket.getId_ticket());
            ps.executeUpdate();

            // Obtener el valor calculado
            String selectSql = "SELECT valor_total FROM Ticket WHERE id_ticket = ?";
            try (PreparedStatement psSelect = con.prepareStatement(selectSql)) {
                psSelect.setInt(1, ticket.getId_ticket());
                ResultSet rs = psSelect.executeQuery();
                if (rs.next()) {
                    ticket.setValor_total(rs.getFloat("valor_total"));
                    System.out.println("✅ Salida registrada (con cobro) - Total: $" + ticket.getValor_total());
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al registrar salida: " + e.getMessage());
            throw new RuntimeException("Error al registrar salida: " + e.getMessage(), e);
        }
    }

    // ✅ NUEVO MÉTODO - Solo registra salida sin calcular (para vehículos CON mensualidad)
    @Override
    public void registrarSalidaConMensualidad(Ticket ticket) {
        String sql = "UPDATE Ticket " +
                "SET hora_salida = GETDATE(), " +
                "    valor_total = 0 " +
                "WHERE id_ticket = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, ticket.getId_ticket());
            ps.executeUpdate();

            ticket.setValor_total(0);
            System.out.println("✅ Salida registrada (mensualidad) - Total: $0");

        } catch (SQLException e) {
            System.err.println("Error al registrar salida con mensualidad: " + e.getMessage());
            throw new RuntimeException("Error al registrar salida: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Ticket> listarHistorial() {
        String sql = "SELECT id_ticket, placa, id_espacio, hora_entrada, hora_salida, valor_total FROM Ticket ORDER BY hora_entrada DESC";
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
