package com.universidad.dao;

import com.universidad.clases.Alumno;
import com.universidad.database.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO {

    public void crearAlumno(Alumno alumno) {

        String sql = " INSERT INTO alumnos (nombre, apellido, ci, fecha_nacimiento, email) VALUES (?, ?, ?, ?,?)";

        try (Connection con = ConexionDB.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getCi());
            ps.setString(4, alumno.getFechaNacimiento());
            ps.setString(5, alumno.getEmail());

            ps.executeUpdate();

            System.out.println("Alumno creado con exito");

        } catch (SQLException e) {

            System.out.println("Error al crear alumno");
            e.printStackTrace();

        }

    }

    public Alumno buscarPorCI(String ci) {

        String sql
                = "SELECT * FROM alumnos WHERE ci=?";

        try (Connection con = ConexionDB.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, ci);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Alumno alumno = new Alumno(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("ci"),
                        rs.getString("fecha_nacimiento"),
                        rs.getString("email")
                );

                return alumno;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;

    }

    public List<Alumno> listarAlumnos() {

        List<Alumno> alumnos = new ArrayList<>();

        String sql
                = "SELECT * FROM alumnos";

        try (Connection con = ConexionDB.getConexion(); Statement st = con.createStatement()) {

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Alumno alumno = new Alumno(
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("ci"),
                        rs.getString("fecha_nacimiento"),
                        rs.getString("email")
                );

                alumno.setNombre(
                        rs.getString("nombre")
                );

                alumno.setApellido(
                        rs.getString("apellido")
                );

                alumno.setCi(
                        rs.getString("ci")
                );

                alumno.setEmail(
                        rs.getString("email")
                );

                alumnos.add(alumno);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return alumnos;

    }

    public void modificarAlumno(Alumno alumno) {

        String sql = "UPDATE alumnos SET nombre=?, apellido=?, email=? WHERE ci=?";

        try (Connection con = ConexionDB.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getEmail());
            ps.setString(4, alumno.getCi());

            ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    public void eliminarAlumno(String ci) {

        String sql
                = "DELETE FROM alumnos WHERE ci=?";

        try (Connection con = ConexionDB.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, ci);

            ps.executeUpdate();

            System.out.println("Alumno eliminado con exito");

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    public List<Alumno> buscarPorNombreApellido(String texto) {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT * FROM alumnos WHERE LOWER(nombre) LIKE ? OR LOWER(apellido) LIKE ?";

        try (Connection con = ConexionDB.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            String busqueda = "%" + texto.toLowerCase() + "%";
            ps.setString(1, busqueda);
            ps.setString(2, busqueda);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Alumno alumno = new Alumno(
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("ci"),
                        rs.getString("fecha_nacimiento"),
                        rs.getString("email")
                );
                alumnos.add(alumno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnos;

    }

    public List<Alumno> listarPorEstadoAcademico(String estado) {
        List<Alumno> alumnos = new ArrayList<>();
        String sql = "";
        if (estado.equalsIgnoreCase("aprobado")) {

            sql = """
        SELECT DISTINCT a.*
        FROM alumnos a
        INNER JOIN calificacion c
        ON a.ci = c.ci_alumno
        WHERE c.nota >= 6
        """;

        } else if (estado.equalsIgnoreCase("desaprobado")) {

            sql = """
        SELECT DISTINCT a.*
        FROM alumnos a
        INNER JOIN calificacion c
        ON a.ci = c.ci_alumno
        WHERE c.nota < 6
        """;

        } else if (estado.equalsIgnoreCase("sin calificacion")) {

            sql = """
        SELECT a.*
        FROM alumnos a
        LEFT JOIN calificacion c
        ON a.ci = c.ci_alumno
        WHERE c.id IS NULL
        """;

        }

        try (Connection con = ConexionDB.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Alumno alumno = new Alumno(rs.getString("nombre"),rs.getString("apellido"),rs.getString("ci"),rs.getString("fecha_nacimiento"),rs.getString("email"));
                alumnos.add(alumno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnos;

    }

}
