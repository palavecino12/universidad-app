package com.universidad.dao;

import com.universidad.clases.Inscripcion;
import com.universidad.database.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionesDAO {

    public void crearInscripcion(Inscripcion inscripcion) {
        String sql =
                "INSERT INTO inscripciones " +
                        "(id_alumno, id_materia, fecha_inscripcion) " +
                        "VALUES (?, ?, ?)";

        try (
                Connection conexion = ConexionDB.getConexion();
                PreparedStatement stmt = conexion.prepareStatement(sql)
        ) {

            stmt.setInt(1, inscripcion.getAlumno().getId());

            stmt.setInt(2, inscripcion.getMateria().getId());

            stmt.setString(3, inscripcion.getFechaInscripcion());

            stmt.executeUpdate();

            System.out.println("Inscripción registrada correctamente.");

        } catch (SQLException e) {

            e.printStackTrace();

        }
    }

    public void eliminarInscripcion(int idAlumno, int idMateria) {}

    public Inscripcion buscarInscripcion(int idAlumno, int idMateria) {}

    public List<Inscripcion> listarPorMateria(int idMateria) {}

}
