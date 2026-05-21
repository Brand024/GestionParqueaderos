package com.GestorParcking.GestionParqueaderos.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    // Corrige el nombre de la base de datos
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=gestionParqueadero;trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PASSWORD = "12345";

    public static Connection conectar() {
        Connection conexion = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error al conectar: " + e.getMessage());
        }
        return conexion;
    }
}