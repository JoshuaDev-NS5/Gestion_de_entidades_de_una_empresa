package org.proyecto.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String URL="jdbc:sqlserver://localhost:1433;databaseName=itam;encrypt=true;trustServerCertificate=true;";
    private static final String USER="sa";
    private static final String pasword="1421";

    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection= DriverManager.getConnection(URL,USER,pasword);
        }catch (ClassNotFoundException e){
            System.out.println("Driver no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Error en la conexion");
            //throw new RuntimeException(e);
        }
        return connection;
    }

}
