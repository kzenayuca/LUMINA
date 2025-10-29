/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ejemplo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Katherin
 */
public class ConexionMySQL {
    // Parámetros de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/lumina_bd";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "Piudz2012";
    
    // Método para obtener la conexión
    public static Connection getConexion() {
        Connection conexion = null;
        try {
            // Cargar el driver de MySQL (opcional en versiones nuevas)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establecer la conexión
            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
            System.out.println("✓ Conexión exitosa a la base de datos");
            
        } catch (ClassNotFoundException e) {
            System.err.println("✗ Error: No se encontró el driver de MySQL");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("✗ Error al conectar con la base de datos");
            e.printStackTrace();
        }
        return conexion;
    }
    
    // Método principal de prueba
    public static void main(String[] args) {
        Connection conexion = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            // Obtener la conexión
            conexion = getConexion();
            
            if (conexion != null) {
                // Crear un statement
                stmt = conexion.createStatement();
                
                // Ejemplo 1: Consultar la versión de MySQL
                rs = stmt.executeQuery("SELECT VERSION()");
                if (rs.next()) {
                    System.out.println("Versión de MySQL: " + rs.getString(1));
                }
                rs.close();
                
                // Ejemplo 2: Mostrar todas las tablas
                System.out.println("\n=== Tablas en la base de datos ===");
                rs = stmt.executeQuery("SHOW TABLES");
                while (rs.next()) {
                    System.out.println("- " + rs.getString(1));
                }
                
                // Ejemplo 3: Consulta de ejemplo (ajusta según tu tabla)
                System.out.println("\n=== Ejemplo de consulta ===");
                // Descomenta y ajusta según tu tabla:
                // rs = stmt.executeQuery("SELECT * FROM tu_tabla LIMIT 5");
                // while (rs.next()) {
                //     System.out.println("ID: " + rs.getInt("id") + 
                //                       " - Nombre: " + rs.getString("nombre"));
                // }
            }
            
        } catch (SQLException e) {
            System.err.println("✗ Error al ejecutar la consulta");
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexion != null) {
                    conexion.close();
                    System.out.println("\n✓ Conexión cerrada correctamente");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
}
