package com.universidad.gestores;

import com.universidad.clases.Calificacion;
import com.universidad.dao.CalificacionesDAO;

import java.util.List;

public class GestorCalificaciones {

    private CalificacionesDAO calificacionesDAO;

    public GestorCalificaciones() {
        this.calificacionesDAO = new CalificacionesDAO();
    }

    public Calificacion buscarPorId(int id) {
        return calificacionesDAO.buscarPorId(id);
    }

    public void cargarNota(Calificacion calificacion) {
        if (calificacion.getNota() < 0 || calificacion.getNota() > 10) {
            System.out.println("La nota debe estar entre 0 y 10");
            return;
        }
        calificacionesDAO.crearCalificacion(calificacion);
        System.out.println("Nota cargada correctamente");
    }

    public void modificarNota(Calificacion calificacion) {
        if (calificacion.getNota() < 0 || calificacion.getNota() > 10) {
            System.out.println("La nota debe estar entre 0 y 10");
            return;
        }
        calificacionesDAO.modificarCalificacion(calificacion);
        System.out.println("Nota modificada correctamente");
    }

    public void eliminarNota(int id) {
        calificacionesDAO.eliminarCalificacion(id);
        System.out.println("Nota eliminada correctamente");
    }

    public List<Calificacion> listarPorAlumno(String ci) {
        return calificacionesDAO.listarPorAlumno(ci);
    }

}
