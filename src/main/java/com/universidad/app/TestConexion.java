package com.universidad.app;

import com.universidad.database.ConexionDB;
import java.sql.Connection;

public class TestConexion {

    public static void main(String[] args) {

        try {

            Connection conexion = ConexionDB.obtenerConexion();

            System.out.println("Conectado correctamente");

            conexion.close();

        } catch (Exception e) {

            System.out.println("Error al conectar");

            System.out.println(e.getMessage());

        }

    }

}