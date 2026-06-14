package com.universidad.gestores;

import com.universidad.clases.Alumno;
import com.universidad.dao.AlumnoDAO;

import java.util.List;

public class GestorAlumnos {

    private AlumnoDAO alumnoDAO;

    public GestorAlumnos() {
        this.alumnoDAO = new AlumnoDAO();
    }

    public void registrarAlumno(Alumno alumno) {

        Alumno existente = alumnoDAO.buscarPorCI(alumno.getCi());

        if (existente != null) {
            System.out.println("Ya existe un alumno con ese CI.");
            return;
        }

        alumnoDAO.crearAlumno(alumno);
    }

    public void modificarAlumno(Alumno alumno) {

        Alumno existente = alumnoDAO.buscarPorCI(alumno.getCi());

        if (existente == null) {
            System.out.println("El alumno no existe.");
            return;
        }

        alumnoDAO.modificarAlumno(alumno);
    }

    public void eliminarAlumno(String ci) {

        Alumno existente = alumnoDAO.buscarPorCI(ci);

        if (existente == null) {
            System.out.println("El alumno no existe.");
            return;
        }

        if (existente.getMateriasInscriptas() != null
                && !existente.getMateriasInscriptas().isEmpty()) {

            System.out.println("El alumno está inscripto a materias.");
            return;
        }

        alumnoDAO.eliminarAlumno(ci);
    }

    public Alumno buscarPorCI(String ci) {

        return alumnoDAO.buscarPorCI(ci);

    }

    public List<Alumno> listarAlumnos() {

        return alumnoDAO.listarAlumnos();

    }

    public List<Alumno> buscarPorNombreApellido(String texto) {

        return alumnoDAO.buscarPorNombreApellido(texto);

    }

    public List<Alumno> listarPorEstadoAcademico(String estado) {

        return alumnoDAO.listarPorEstadoAcademico(estado);

    }
}
