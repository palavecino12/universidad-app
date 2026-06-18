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
                Connection conexion = ConexionDB.getConexion(); PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setInt(1, inscripcion.getAlumno().getId());
            stmt.setInt(2, inscripcion.getMateria().getId());
            stmt.setString(3, inscripcion.getFechaInscripcion());

            stmt.executeUpdate();

            System.out.println("Inscripción registrada correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int buscarIdInscripcion(String idAlumno, String idMateria) {

        String sql = """
        SELECT id 
        FROM inscripciones
        WHERE id_alumno = ?
        AND id_materia = ?
        """;

        try (
                Connection con = ConexionDB.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, idAlumno);
            ps.setString(2, idMateria);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public void eliminarInscripcion(int idAlumno, int idMateria) {

        String sql
                = "DELETE FROM inscripciones "
                + "WHERE id_alumno = ? "
                + "AND id_materia = ?";

        try (
                Connection conexion = ConexionDB.getConexion(); PreparedStatement stmt
                = conexion.prepareStatement(sql)) {

            stmt.setInt(1, idAlumno);
            stmt.setInt(2, idMateria);

            int filasAfectadas
                    = stmt.executeUpdate();

            if (filasAfectadas > 0) {

                System.out.println(
                        "Inscripción eliminada correctamente."
                );

            } else {

                System.out.println(
                        "No se encontró la inscripción."
                );

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }
    }

    public Inscripcion buscarInscripcion(String idAlumno, String codigo_materia) {

        String sql
                = "SELECT * FROM inscripciones "
                + "WHERE id_alumno = ? "
                + "AND id_materia = ?";

        try (
                Connection conexion = ConexionDB.getConexion(); PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setString(1, idAlumno);
            stmt.setString(2, codigo_materia);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                int id = rs.getInt("id");

                String fechaInscripcion = rs.getString("fecha_inscripcion");

                AlumnoDAO alumnoDAO = new AlumnoDAO();

                MateriaDAO materiaDAO = new MateriaDAO();

                Alumno alumno = alumnoDAO.buscarPorCI(idAlumno);

                Materia materia = materiaDAO.buscarPorCodigo(codigo_materia);

                return new Inscripcion(
                        id,
                        alumno,
                        materia,
                        fechaInscripcion
                );
            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;
    }

    public List<Inscripcion> listarPorMateria(String codigo_materia) {

        List<Inscripcion> inscripciones
                = new ArrayList<>();

        String sql
                = "SELECT * FROM inscripciones "
                + "WHERE id_materia = ?";

        try (
                Connection conexion = ConexionDB.getConexion(); PreparedStatement stmt
                = conexion.prepareStatement(sql)) {

            stmt.setString(1, codigo_materia);

            ResultSet rs = stmt.executeQuery();

            AlumnoDAO alumnoDAO
                    = new AlumnoDAO();

            MateriaDAO materiaDAO
                    = new MateriaDAO();

            while (rs.next()) {

                int id
                        = rs.getInt("id");

                int idAlumno
                        = rs.getInt("id_alumno");

                String fechaInscripcion
                        = rs.getString("fecha_inscripcion");

                Alumno alumno
                        = alumnoDAO.buscarPorId(idAlumno);

                Materia materia
                        = materiaDAO.buscarPorCodigo(codigo_materia);

                Inscripcion inscripcion
                        = new Inscripcion(
                                id,
                                alumno,
                                materia,
                                fechaInscripcion
                        );

                inscripciones.add(inscripcion);
            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return inscripciones;
    }

    public Inscripcion buscarPorId(int idInscripcion) {

        String sql = "SELECT * FROM inscripciones WHERE id=?";

        try (Connection con = ConexionDB.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

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

    public boolean existeInscripcion(int idAlumno, int idMateria) {

        String sql = "SELECT * FROM inscripciones WHERE id_alumno = ? AND id_materia = ?";

        try (
                Connection conexion = ConexionDB.getConexion(); PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setInt(1, idAlumno);
            stmt.setInt(2, idMateria);

            ResultSet rs = stmt.executeQuery();

            return rs.next(); // si encuentra una fila devuelve true

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    public boolean tieneInscripciones(int idAlumno) {

        String sql
                = "SELECT COUNT(*) "
                + "FROM inscripciones "
                + "WHERE id_alumno = ?";

        try (
                Connection conexion = ConexionDB.getConexion(); PreparedStatement stmt
                = conexion.prepareStatement(sql)) {

            stmt.setInt(1, idAlumno);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return rs.getInt(1) > 0;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;
    }

    public int contarInscriptosPorMateria(String codigo) {
        // Cambiamos 'WHERE id = ?' por 'WHERE codigo_materia = ?' 
        // (Asegúrate de que el nombre de la columna en tu tabla sea 'codigo_materia' o como se llame en tu BD)
        String sql = "SELECT COUNT(*) FROM inscripciones WHERE id_materia = ?";

        try (Connection con = ConexionDB.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            // Aquí envías el String que recibes como parámetro
            ps.setString(1, codigo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al contar inscriptos: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

}
