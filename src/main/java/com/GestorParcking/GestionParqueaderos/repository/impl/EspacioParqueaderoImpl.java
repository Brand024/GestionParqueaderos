package com.GestorParcking.GestionParqueaderos.repository.impl;

import com.GestorParcking.GestionParqueaderos.models.EspacioParqueadero;
import com.GestorParcking.GestionParqueaderos.repository.IEspacioParqueaderoDao;
import com.GestorParcking.GestionParqueaderos.util.Conexion;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EspacioParqueaderoImpl implements IEspacioParqueaderoDao {

    @Override
    public List<EspacioParqueadero> listarDisponibles() {  // Consulta SQL para obtener todos los espacios que están disponibles (estado = 1)
        String sql = "SELECT id_espacio, numero, piso, tipo_celda, estado FROM EspacioParqueadero WHERE estado = 1";
        List<EspacioParqueadero> lista = new ArrayList<>();

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {  // Recorremos el resultado y construimos la lista de espacios disponibles
                EspacioParqueadero e = new EspacioParqueadero();
                e.setId_espacio(rs.getInt("id_espacio"));
                e.setNumero(rs.getInt("numero"));
                e.setPiso(rs.getInt("piso"));
                e.setEstado(rs.getBoolean("estado"));
                lista.add(e);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar espacios: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void actualizarEstado(int idEspacio, boolean estaDisponible) {  // Actualiza el estado de un espacio (true = disponible, false = ocupado)
        String sql = "UPDATE EspacioParqueadero SET estado = ? WHERE id_espacio = ?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Corrección: usamos setBoolean para el tipo BIT
            ps.setBoolean(1, estaDisponible);
            ps.setInt(2, idEspacio);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al cambiar estado de celda: " + e.getMessage());
        }
    }

    @Override
    public EspacioParqueadero buscarPorNumero(int numero) {  // Busca un espacio de parqueadero según su número
        String sql = "SELECT id_espacio, numero, piso, tipo_celda, estado FROM EspacioParqueadero WHERE numero = ?";
        EspacioParqueadero espacio = null;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, numero);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {  // Construimos el objeto EspacioParqueadero con los datos obtenidos
                espacio = new EspacioParqueadero();
                espacio.setId_espacio(rs.getInt("id_espacio"));
                espacio.setNumero(rs.getInt("numero"));
                espacio.setPiso(rs.getInt("piso"));
                espacio.setEstado(rs.getBoolean("estado"));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar espacio: " + e.getMessage());
        }
        return espacio;
    }
}
