package com.universidad.dao;

import com.universidad.database.ConexionDB;
import com.universidad.clases.Materia;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class MateriaDAO {

    public void crearMateria(Materia materia) {
        String sql = "INSERT INTO materias(nombre, codigo, cupo_maximo) VALUES (?, ?, ?)";

        try (
                //Este bloque antes de las {}, sirve para que todo lo que iniciemos aca, se cierra automaticamente al final
                Connection conexion = ConexionDB.getConexion(); PreparedStatement stmt = conexion.prepareStatement(sql) //Prepara la consulta: stmt = a la consulta sin los valores
                ) {
            stmt.setString(1, materia.getNombre()); //Va aplicando los valores a la consulta
            stmt.setString(2, materia.getCodigo());
            stmt.setInt(3, materia.getCupoMaximo());

            stmt.executeUpdate(); //Ejecutamos la contula con los valores

            System.out.println("Materia registrada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al registrar materia.");
            e.printStackTrace(); //Muetra el error tecnico completo
        }
    }

    public Materia buscarPorCodigo(String codigo) {
        String sql = "SELECT * FROM materias WHERE codigo = ?";

        try (
                Connection conexion = ConexionDB.getConexion(); PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setString(1, codigo);

            //Crea un objeto que representa las filas devueltas por MySQL
            ResultSet rs = stmt.executeQuery();

            //Retorna true o false si encontro algo o no
            if (rs.next()) {

                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String codigoMateria = rs.getString("codigo");
                int cupo = rs.getInt("cupo_maximo");

                return new Materia(id, nombre, codigoMateria, cupo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Materia> listarMaterias() {
        List<Materia> materias = new ArrayList<>();

        String sql = "SELECT * FROM materias";

        try (
                Connection conexion = ConexionDB.getConexion(); PreparedStatement stmt = conexion.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                String nombre = rs.getString("nombre");
                String codigo = rs.getString("codigo");
                int cupo = rs.getInt("cupo_maximo");

                Materia materia = new Materia(nombre, codigo, cupo);

                materias.add(materia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return materias;
    }

    public void modificarMateria(Materia materia) {
        String sql = "UPDATE materias SET nombre = ?, cupo_maximo = ? WHERE codigo = ?";

        try (
                Connection conexion = ConexionDB.getConexion(); PreparedStatement stmt = conexion.prepareStatement(sql)) {

            stmt.setString(1, materia.getNombre());
            stmt.setInt(2, materia.getCupoMaximo());
            stmt.setString(3, materia.getCodigo());

            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Materia modificada correctamente.");
            } else {
                System.out.println("No existe una materia con ese código.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarMateria(String codigo) {

        try (
                Connection conexion = ConexionDB.getConexion()) {

            conexion.setAutoCommit(false);

            // 1) Buscar el id de la materia por código
            String buscarId = "SELECT id FROM materias WHERE codigo = ?";

            PreparedStatement stmtBuscar = conexion.prepareStatement(buscarId);
            stmtBuscar.setString(1, codigo);

            ResultSet rs = stmtBuscar.executeQuery();

            if (!rs.next()) {
                System.out.println("No existe una materia con ese código.");
                return;
            }

            int idMateria = rs.getInt("id");

            // 2) Borrar inscripciones de esa materia
            String borrarInscripciones
                    = "DELETE FROM inscripciones WHERE id_materia = ?";

            PreparedStatement stmtInscripciones
                    = conexion.prepareStatement(borrarInscripciones);

            stmtInscripciones.setInt(1, idMateria);
            stmtInscripciones.executeUpdate();

            // 3) Borrar materia
            String borrarMateria
                    = "DELETE FROM materias WHERE codigo = ?";

            PreparedStatement stmtMateria
                    = conexion.prepareStatement(borrarMateria);

            stmtMateria.setString(1, codigo);

            int filasAfectadas = stmtMateria.executeUpdate();

            if (filasAfectadas > 0) {
                conexion.commit();
                System.out.println("Materia eliminada correctamente.");
            } else {
                conexion.rollback();
                System.out.println("No se pudo eliminar la materia.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
