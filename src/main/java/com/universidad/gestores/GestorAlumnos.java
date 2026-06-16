package com.universidad.gestores;

import com.universidad.clases.Alumno;
import com.universidad.dao.AlumnoDAO;
import com.universidad.exceptions.AlumnoDuplicadoException;
import com.universidad.exceptions.AlumnoNoEncontradoException;

import java.util.List;

public class GestorAlumnos {

    private AlumnoDAO alumnoDAO;

    public GestorAlumnos() {
        this.alumnoDAO = new AlumnoDAO();
    }

    public Alumno buscarPorId(int id) {

        return alumnoDAO.buscarPorId(id);

    }

    public void registrarAlumno(Alumno alumno) throws AlumnoDuplicadoException {

        Alumno existente = alumnoDAO.buscarPorCI(alumno.getCi());

        if (existente != null) {
            throw new AlumnoDuplicadoException();
        }

        alumnoDAO.crearAlumno(alumno);
    }

    public void modificarAlumno(Alumno alumno) throws AlumnoNoEncontradoException {

        Alumno existente = alumnoDAO.buscarPorCI(alumno.getCi());

        if (existente == null) {
            throw new AlumnoNoEncontradoException();
        }

        alumnoDAO.modificarAlumno(alumno);
    }

    public void eliminarAlumno(String ci) throws AlumnoNoEncontradoException {

        Alumno existente = alumnoDAO.buscarPorCI(ci);

        if (existente == null) {
            throw new AlumnoNoEncontradoException();
        }

        if (existente.getMateriasInscriptas() != null && !existente.getMateriasInscriptas().isEmpty()) {
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
