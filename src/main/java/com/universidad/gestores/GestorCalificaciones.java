package com.universidad.gestores;

import com.universidad.clases.Alumno;
import com.universidad.clases.Calificacion;
import com.universidad.clases.Inscripcion;
import com.universidad.clases.Materia;
import com.universidad.dao.CalificacionesDAO;

import java.util.List;

public class GestorCalificaciones {

    private CalificacionesDAO calificacionesDAO;
    private Inscripcion i;
    private GestorInscripcion gi = new GestorInscripcion();

    public GestorCalificaciones() {
        this.calificacionesDAO = new CalificacionesDAO();
    }

    public Calificacion buscarPorId(int id) {
        return calificacionesDAO.buscarPorId(id);
    }

    public void cargarNota(Calificacion calificacion, Alumno alumno, Materia m) {

        if (calificacion.getNota() < 0 || calificacion.getNota() > 10) {
            System.out.println("La nota debe estar entre 0 y 10");
            return;
        }
        i = gi.buscarInscripcion(alumno, m);
        if (i == null) {
            System.out.println("No existe inscripción");
            return;
        }

        calificacionesDAO.crearCalificacion(calificacion,i.getId());

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
