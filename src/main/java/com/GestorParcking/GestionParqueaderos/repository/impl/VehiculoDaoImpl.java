package com.GestorParcking.GestionParqueaderos.repository.impl;

import com.GestorParcking.GestionParqueaderos.models.Vehiculo;
import com.GestorParcking.GestionParqueaderos.repository.IVehiculoDao;
import com.GestorParcking.GestionParqueaderos.util.Conexion;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class VehiculoDaoImpl implements IVehiculoDao {
    @Override
    public void registrar(Vehiculo vehiculo) {
        String sql = "INSERT INTO Vehiculo (placa, id_tipo, modelo, color) VALUES (?, ?, ?, ?)"; // Sentencia SQL para insertar un nuevo vehículo

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Llenamos los "?" con los datos del objeto vehiculo
            ps.setString(1, vehiculo.getPlaca());
            ps.setInt(2, vehiculo.getIdTipo());
            ps.setString(3, vehiculo.getModelo());
            ps.setString(4, vehiculo.getColor());

            ps.executeUpdate(); // Ejecuta la inserción en SQL Server
            System.out.println("Vehículo registrado con éxito.");

        } catch (SQLException e) { 
            System.err.println("Error al registrar vehículo: " + e.getMessage());
        }
    }

    @Override
    public Vehiculo buscarPorPlaca(String placa) {
        String sql = "SELECT * FROM Vehiculo WHERE placa = ?"; // Consulta SQL para buscar un vehículo por su placa
        Vehiculo vehiculo = null;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, placa);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                vehiculo = new Vehiculo();  // Construimos el objeto Vehiculo con los datos obtenidos
                vehiculo.setPlaca(rs.getString("placa"));
                vehiculo.setIdTipo(rs.getInt("id_tipo"));
                vehiculo.setModelo(rs.getString("modelo"));
                vehiculo.setColor(rs.getString("color"));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar vehículo: " + e.getMessage());
        }
        return vehiculo;
    }


    @Override
    public List<Vehiculo> listarTodos() {  // Consulta SQL para obtener todos los vehículos
        String sql = "SELECT * FROM Vehiculo";
        List<Vehiculo> lista = new ArrayList<>();

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
// Recorremos el resultado y construimos la lista de vehículos
            while (rs.next()) {
                Vehiculo v = new Vehiculo();
                v.setPlaca(rs.getString("placa"));
                v.setIdTipo(rs.getInt("id_tipo"));
                v.setModelo(rs.getString("modelo"));
                v.setColor(rs.getString("color"));
                lista.add(v);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar vehículos: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizar(Vehiculo vehiculo) { // Sentencia SQL para actualizar los datos de un vehículo
        String sql = "UPDATE Vehiculo SET id_tipo = ?, modelo = ?, color = ? WHERE placa = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, vehiculo.getIdTipo());
            ps.setString(2, vehiculo.getModelo());
            ps.setString(3, vehiculo.getColor());
            ps.setString(4, vehiculo.getPlaca());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Vehículo actualizado correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar vehículo: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(String placa) {  // Sentencia SQL para eliminar un vehículo por su placa
        String sql = "DELETE FROM Vehiculo WHERE placa = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, placa);
            ps.executeUpdate();
            System.out.println("Vehículo eliminado de la base de datos.");

        } catch (SQLException e) {
            System.err.println("Error al eliminar vehículo: " + e.getMessage());
        }
    }
}
