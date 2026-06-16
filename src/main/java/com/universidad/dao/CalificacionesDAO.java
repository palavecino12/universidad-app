package com.universidad.dao;

import com.universidad.clases.Calificacion;
import com.universidad.clases.Materia;
import com.universidad.database.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalificacionesDAO {

    public void crearCalificacion(Calificacion calificacion) {

        String buscarInscripcion = """
            SELECT i.id
            FROM inscripciones i
            INNER JOIN materias m
            ON i.id_materia = m.id
            WHERE m.id = ?
            """;

        String insertar = """
            INSERT INTO calificaciones
            (id_inscripcion, nota, fecha)
            VALUES (?, ?, ?)
            """;

        try (Connection con = ConexionDB.getConexion()) {

            PreparedStatement ps1 = con.prepareStatement(buscarInscripcion);

            ps1.setInt(1, calificacion.getMateria().getId());

            ResultSet rs = ps1.executeQuery();

            if (rs.next()) {

                int idInscripcion = rs.getInt("id");

                PreparedStatement ps2 = con.prepareStatement(insertar);

                ps2.setInt(1, idInscripcion);
                ps2.setDouble(2, calificacion.getNota());
                ps2.setString(3, calificacion.getFecha());

                ps2.executeUpdate();

            } else {

                System.out.println("No existe inscripción para esa materia.");

            }

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
        String sql = """
                UPDATE calificaciones
                SET nota=?, fecha=?
                WHERE id=?
                """;
        try (Connection con = ConexionDB.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, calificacion.getNota());
            ps.setString(2, calificacion.getFecha());
            ps.setInt(3, calificacion.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
