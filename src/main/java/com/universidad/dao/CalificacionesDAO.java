package com.universidad.dao;

import com.universidad.clases.Calificacion;
import com.universidad.database.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalificacionesDAO {

    public void crearCalificacion(Calificacion calificacion) {
        String sql = """
                INSERT INTO calificacion
                (nota, fecha, codigo_materia)
                VALUES (?, ?, ?)
                """;

        try (Connection con = ConexionDB.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, calificacion.getNota());
            ps.setString(2, calificacion.getFecha());
            ps.setString(4, calificacion.getMateria().getCodigo());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Calificacion buscarPorId(int id) {
        String sql = "SELECT * FROM calificacion WHERE id=?";
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
                UPDATE calificacion
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

        String sql = "DELETE FROM calificacion WHERE id=?";

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
                SELECT *
                FROM calificacion
                WHERE ci_alumno=?
                """;
        try (Connection con = ConexionDB.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ci);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Calificacion calificacion = new Calificacion(rs.getInt("id"), rs.getDouble("nota"), rs.getString("fecha"), null);
                lista.add(calificacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;

    }

}
