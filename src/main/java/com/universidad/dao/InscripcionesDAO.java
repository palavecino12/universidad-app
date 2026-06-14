package com.universidad.dao;

import com.universidad.clases.Inscripcion;
import com.universidad.database.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionesDAO {

    public void crearInscripcion(Inscripcion inscripcion) {

        String sql
                = """
        INSERT INTO inscripcion
        (ci_alumno, codigo_materia, fechaInscripcion)
        VALUES (?, ?, ?)
        """;

        try (Connection con = ConexionDB.obtenerConexion(); PreparedStatement ps
                = con.prepareStatement(sql)) {

            ps.setString(1,inscripcion.getAlumno().getCi());
            ps.setString(2,inscripcion.getMateria().getCodigo());
            ps.setString(3, inscripcion.getFechaInscripcion());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Inscripcion> listarInscripciones() {
        
        List<Inscripcion> lista = new ArrayList<>();
        return lista;

    }

}
