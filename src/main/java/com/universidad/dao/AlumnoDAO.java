package com.universidad.dao;

import com.universidad.clases.Alumno;
import com.universidad.database.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO {

    public void crearAlumno(Alumno alumno) {

        String sql = """
                INSERT INTO alumno
                (nombre, apellido, ci, email)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getCi());
            ps.setString(4, alumno.getEmail());

            ps.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Error al crear alumno");
            e.printStackTrace();

        }

    }

    public Alumno buscarPorCI(String ci) {

        String sql
                = "SELECT * FROM alumno WHERE ci=?";

        try (Connection con = ConexionDB.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, ci);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Alumno alumno = new Alumno(
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("ci"),
                        rs.getString("fechaNacimiento"),
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
                = "SELECT * FROM alumno";

        try (Connection con = ConexionDB.getConexion(); Statement st = con.createStatement()) {

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                Alumno alumno = new Alumno(
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("ci"),
                        rs.getString("fechaNacimiento"),
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

        String sql = """
                UPDATE alumno
                SET nombre=?, apellido=?, email=?
                WHERE ci=?
                """;

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
                = "DELETE FROM alumno WHERE ci=?";

        try (Connection con = ConexionDB.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, ci);

            ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

}
