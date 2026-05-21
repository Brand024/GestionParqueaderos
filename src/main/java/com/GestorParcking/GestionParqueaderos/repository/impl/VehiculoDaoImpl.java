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
        String sql = "INSERT INTO Vehiculo (placa, id_tipo, modelo, color) VALUES (?, ?, ?, ?)";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, vehiculo.getPlaca().toUpperCase());
            ps.setInt(2, vehiculo.getIdTipo());
            ps.setString(3, vehiculo.getModelo());
            ps.setString(4, vehiculo.getColor());
            ps.executeUpdate();
            System.out.println("Vehículo registrado: " + vehiculo.getPlaca());
        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar vehículo: " + e.getMessage(), e);
        }
    }

    @Override
    public Vehiculo buscarPorPlaca(String placa) {
        String sql = "SELECT placa, modelo, color, id_tipo FROM Vehiculo WHERE placa = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, placa.toUpperCase());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Vehiculo v = new Vehiculo();
                v.setPlaca(rs.getString("placa"));
                v.setModelo(rs.getString("modelo"));
                v.setColor(rs.getString("color"));
                v.setIdTipo(rs.getInt("id_tipo"));
                return v;
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar vehículo: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Vehiculo> listarTodos() {
        String sql = "SELECT placa, modelo, color, id_tipo FROM Vehiculo";
        List<Vehiculo> lista = new ArrayList<>();
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Vehiculo v = new Vehiculo();
                v.setPlaca(rs.getString("placa"));
                v.setModelo(rs.getString("modelo"));
                v.setColor(rs.getString("color"));
                v.setIdTipo(rs.getInt("id_tipo"));
                lista.add(v);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar vehículos: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizar(Vehiculo vehiculo) {
        String sql = "UPDATE Vehiculo SET id_tipo = ?, modelo = ?, color = ? WHERE placa = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, vehiculo.getIdTipo());
            ps.setString(2, vehiculo.getModelo());
            ps.setString(3, vehiculo.getColor());
            ps.setString(4, vehiculo.getPlaca().toUpperCase());
            ps.executeUpdate();
            System.out.println("Vehículo actualizado: " + vehiculo.getPlaca());
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar vehículo: " + e.getMessage(), e);
        }
    }

    @Override
    public void eliminar(String placa) {
        String sql = "DELETE FROM Vehiculo WHERE placa = ?";
        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, placa.toUpperCase());
            ps.executeUpdate();
            System.out.println("Vehículo eliminado: " + placa);
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar vehículo: " + e.getMessage(), e);
        }
    }
}
