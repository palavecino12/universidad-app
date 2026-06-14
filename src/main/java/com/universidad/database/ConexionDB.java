/**
 * Esta clase es la encargada de abrir conexiones con la base de datos.
 * Utiliza la configuración definida en DatabaseConfig.
 * Los DAO utilizarán esta clase para obtener conexiones y ejecutar consultas SQL.
 * java.sql forma parte de JDBC.
 */
package com.universidad.database;

import java.sql.Connection;      //Es un type que representa una conexión abierta con la base de datos.
import java.sql.DriverManager;   //Permite crear conexiones JDBC.
import java.sql.SQLException;    //Error relacionado con operaciones SQL.

public class ConexionDB {

    public static Connection getConexion()//Metodo que va a crear la conexion y retornarla.
            throws SQLException {

        //Intenta conectarse utilizando la URL, usuario y contraseña configurados.
        return DriverManager.getConnection(
                DatabaseConfig.URL,
                DatabaseConfig.USER,
                DatabaseConfig.PASSWORD
        );
    }

}