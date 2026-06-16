package com.universidad.dao;

import com.universidad.clases.Calificacion;
import com.universidad.clases.Materia;
import com.universidad.database.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalificacionesDAO {

    public void crearCalificacion(Calificacion calificacion) {
        String sql = """
                INSERT INTO calificaciones
                (id_inscripcion, nota, fecha)
                VALUES (?, ?, ?)
                """;

        try (Connection con = ConexionDB.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(2, calificacion.getNota());
            ps.setString(3, calificacion.getFecha());
            ps.setString(1, calificacion.getMateria().getCodigo());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Calificacion buscarPorId(int id) {
        String sql = "SELECT * FROM calificaciones WHERE id=?";
        try (Connection con = ConexionDB.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Calificacion(
                        rs.getInt("id"),
                        rs.getDouble("nota"),
                        rs.getString("fecha"),
                        null
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void modificarCalificacion(Calificacion calificacion) {
        String sql = "UPDATE calificaciones SET nota=?, fecha=? WHERE id=?";

        try (Connection con = ConexionDB.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, calificacion.getNota());
            ps.setDate(2, java.sql.Date.valueOf(calificacion.getFecha()));
            ps.setInt(3, calificacion.getId());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                System.out.println("No se encontró ningún registro con ID: " + calificacion.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Aquí verás si hay un error de sintaxis o de formato
        }
    }

    public void eliminarCalificacion(int id) {

        String sql = "DELETE FROM calificaciones WHERE id=?";

        try (Connection con = ConexionDB.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Calificacion> listarPorAlumno(String ci) {

        List<Calificacion> lista = new ArrayList<>();

        String sql = """
                SELECT c.*, m.id as id_materia, m.nombre, m.codigo, m.cupo_maximo
                FROM calificaciones c
                INNER JOIN inscripciones i
                ON c.id_inscripcion = i.id
                INNER JOIN alumnos a
                ON i.id_alumno = a.id
                INNER JOIN materias m
                ON i.id_materia = m.id
                WHERE a.ci = ?
                """;

        try (Connection con = ConexionDB.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, ci);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Materia materia = new Materia(
                        rs.getInt("id_materia"),
                        rs.getString("nombre"),
                        rs.getString("codigo"),
                        rs.getInt("cupo_maximo")
                );

                Calificacion calificacion
                        = new Calificacion(
                                rs.getInt("id"),
                                rs.getDouble("nota"),
                                rs.getString("fecha"),
                                materia
                        );

                lista.add(calificacion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
