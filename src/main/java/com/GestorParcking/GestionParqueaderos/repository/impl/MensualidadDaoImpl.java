package com.GestorParcking.GestionParqueaderos.repository.impl;

import com.GestorParcking.GestionParqueaderos.models.Mensualidad;
import com.GestorParcking.GestionParqueaderos.repository.IMensualidadDao;
import com.GestorParcking.GestionParqueaderos.util.Conexion;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MensualidadDaoImpl implements IMensualidadDao {

    @Override
    public void registrar(Mensualidad mensualidad) {  // Inserta una nueva mensualidad con sus fechas, valor y estado de pago
        String sql = "INSERT INTO Mensualidad (placa, fecha_inicio, fecha_fin, valor, pagado) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Asignamos los valores del objeto Mensualidad a la sentencia SQL
            ps.setString(1, mensualidad.getPlaca());
            ps.setDate(2, mensualidad.getFecha_inicio());
            ps.setDate(3, mensualidad.getFecha_fin());
            ps.setFloat(4, mensualidad.getValor());
            ps.setBoolean(5, mensualidad.isPagado());

            ps.executeUpdate();
            System.out.println("Mensualidad registrada con éxito.");

        } catch (SQLException e) {
            System.err.println("Error al registrar mensualidad: " + e.getMessage());
        }
    }

    @Override
    public List<Mensualidad> listarTodas() {  // Consulta SQL para obtener todas las mensualidades
        String sql = "SELECT * FROM Mensualidad";
        List<Mensualidad> lista = new ArrayList<>();

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {  // Recorremos el resultado y construimos la lista de objetos Mensualidad
                Mensualidad m = new Mensualidad();
                m.setId_mensualidad(rs.getInt("id_mensualidad"));
                m.setPlaca(rs.getString("placa"));
                // Leemos el dato como Date de SQL
                m.setFecha_inicio(rs.getDate("fecha_inicio"));
                m.setFecha_fin(rs.getDate("fecha_fin"));
                m.setValor(rs.getFloat("valor"));
                m.setPagado(rs.getBoolean("pagado"));
                lista.add(m);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar mensualidades: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public Mensualidad buscarPorPlaca(String placa) {  // Busca una mensualidad asociada a la placa de un vehículo
        String sql = "SELECT * FROM Mensualidad WHERE placa = ?";
        Mensualidad m = null;
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, placa);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {  // Construimos el objeto Mensualidad con los datos obtenidos
                m = new Mensualidad();
                m.setId_mensualidad(rs.getInt("id_mensualidad"));
                m.setPlaca(rs.getString("placa"));
                m.setFecha_inicio(rs.getDate("fecha_inicio"));
                m.setFecha_fin(rs.getDate("fecha_fin"));
                m.setValor(rs.getFloat("valor"));
                m.setPagado(rs.getBoolean("pagado"));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar mensualidad: " + e.getMessage());
        }
        return m;
    }

    @Override
    public void eliminar(int idMensualidad) {  // Elimina una mensualidad según su identificador único
        String sql = "DELETE FROM Mensualidad WHERE id_mensualidad = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idMensualidad);
            ps.executeUpdate();
            System.out.println("Mensualidad eliminada.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
        }
    }

    @Override
    public void actualizar(Mensualidad mensualidad) {  // Actualiza los datos de una mensualidad existente
        String sql = "UPDATE Mensualidad SET placa = ?, fecha_inicio = ?, fecha_fin = ?, valor = ?, pagado = ? WHERE id_mensualidad = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, mensualidad.getPlaca());
            ps.setDate(2, mensualidad.getFecha_inicio());
            ps.setDate(3, mensualidad.getFecha_fin());
            ps.setFloat(4, mensualidad.getValor());
            ps.setBoolean(5, mensualidad.isPagado());
            ps.setInt(6, mensualidad.getId_mensualidad());

            ps.executeUpdate();
            System.out.println("Mensualidad actualizada en la base de datos.");

        } catch (SQLException e) {
            System.err.println("Error al actualizar mensualidad: " + e.getMessage());
        }
    }
}
