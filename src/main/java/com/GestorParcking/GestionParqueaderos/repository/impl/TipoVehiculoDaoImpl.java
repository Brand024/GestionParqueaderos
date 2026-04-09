package com.GestorParcking.GestionParqueaderos.repository.impl;

import com.GestorParcking.GestionParqueaderos.models.TipoVehiculo;
import com.GestorParcking.GestionParqueaderos.repository.ITipoVehiculoDao;
import com.GestorParcking.GestionParqueaderos.util.Conexion;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TipoVehiculoDaoImpl implements ITipoVehiculoDao {

    @Override
    public List<TipoVehiculo> listarTipos() {
        String sql = "SELECT * FROM TipoVehiculo";
        List<TipoVehiculo> lista = new ArrayList<>();

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TipoVehiculo tv = new TipoVehiculo();
                tv.setId_tipo(rs.getInt("id_tipo"));
                tv.setNombre(rs.getString("nombre_tipo"));
                tv.setTarifa_minuto(rs.getFloat("tarifa_minuto"));
                lista.add(tv);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar tipos: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public TipoVehiculo buscarPorId(int idTipo) {
        String sql = "SELECT * FROM TipoVehiculo WHERE id_tipo = ?";
        TipoVehiculo tipo = null;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idTipo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tipo = new TipoVehiculo();
                tipo.setId_tipo(rs.getInt("id_tipo"));
                tipo.setNombre(rs.getString("nombre_tipo"));
                tipo.setTarifa_minuto(rs.getFloat("tarifa_minuto"));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar tipo: " + e.getMessage());
        }
        return tipo;
    }
}