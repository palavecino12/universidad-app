package com.universidad.dao;

import com.universidad.clases.Alumno;
import com.universidad.clases.Inscripcion;
import com.universidad.clases.Materia;
import com.universidad.database.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionesDAO {

    public void crearInscripcion(Inscripcion inscripcion) {
        String sql = "INSERT INTO inscripciones (id_alumno, id_materia, fecha_inscripcion) VALUES (?, ?, ?)";

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

    public void eliminarInscripcion(int idAlumno, int idMateria) {
        String sql = "DELETE FROM inscripciones WHERE id_alumno=? AND id_materia=?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);

            ps.executeUpdate();

            System.out.println("Inscripción eliminada con éxito");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Inscripcion buscarInscripcion(int idAlumno, int idMateria) {

        String sql = "SELECT * FROM inscripciones WHERE id_alumno=? AND id_materia=?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Alumno alumno = new Alumno(idAlumno, null, null, null, null, null);
                Materia materia = new Materia(idMateria, null, null, 0);

                Inscripcion inscripcion = new Inscripcion(
                        rs.getInt("id"),
                        alumno,
                        materia,
                        rs.getDate("fecha_inscripcion").toString()
                );

                return inscripcion;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Inscripcion> listarPorMateria(int idMateria) {
        List<Inscripcion> lista = new ArrayList<>();

        String sql = "SELECT * FROM inscripciones WHERE id_materia=?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, idMateria);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int idInscripcion = rs.getInt("id");
                int idAlumno = rs.getInt("id_alumno");
                Date fecha = rs.getDate("fecha_inscripcion");

                Alumno alumno = new Alumno(idAlumno, null, null, null, null, null);
                Materia materia = new Materia(idMateria, null, null, 0);

                Inscripcion inscripcion = new Inscripcion(idInscripcion,alumno, materia, fecha.toString());

                lista.add(inscripcion);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;}

    public Inscripcion buscarPorId(int idInscripcion) {

        String sql = "SELECT * FROM inscripciones WHERE id=?";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idInscripcion);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int idAlumno = rs.getInt("id_alumno");
                int idMateria = rs.getInt("id_materia");

                Alumno alumno = new Alumno(idAlumno, null, null, null, null, null);
                Materia materia = new Materia(idMateria, null, null, 0);

                return new Inscripcion(idInscripcion, alumno, materia, rs.getDate("fecha_inscripcion").toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
