package com.universidad.dao;

import com.universidad.clases.Docente;
import com.universidad.database.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocenteDAO {

    public void crearDocente(Docente docente) {

        String sql = """
                INSERT INTO docente
                (nombre, apellido, ci, especialidad)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection con = ConexionDB.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, docente.getNombre());

            ps.setString(2, docente.getApellido());

            ps.setString(3, docente.getCi());

            ps.setString(4, docente.getEspecialidad());

            ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    public void modificarDocente(Docente docente) {

        String sql = """
                UPDATE docente
                SET nombre=?,
                    apellido=?,
                    especialidad=?
                WHERE ci=?
                """;

        try (Connection con = ConexionDB.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, docente.getNombre());

            ps.setString(2, docente.getApellido());

            ps.setString(3, docente.getEspecialidad());

            ps.setString(4, docente.getCi());

            ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    public void eliminarDocente(String ci) {

        String sql
                = "DELETE FROM docente WHERE ci=?";

        try (Connection con = ConexionDB.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, ci);

            ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    public List<Docente> listarDocentes() {

        List<Docente> lista = new ArrayList<>();

        String sql
                = "SELECT * FROM docente";

        try (Connection con = ConexionDB.getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Docente docente
                        = new Docente(
                                rs.getString("nombre"),
                                rs.getString("apellido"),
                                rs.getString("ci"),
                                rs.getString("especialidad")
                        );

                lista.add(docente);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return lista;

    }

}
