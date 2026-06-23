package com.universidad.gestores;

import com.universidad.clases.Alumno;
import com.universidad.clases.Materia;
import com.universidad.dao.AlumnoDAO;
import com.universidad.dao.InscripcionesDAO;
import com.universidad.exceptions.AlumnoDuplicadoException;
import com.universidad.exceptions.AlumnoNoEncontradoException;

import java.util.List;

public class GestorAlumnos {

    private AlumnoDAO alumnoDAO;
    private InscripcionesDAO inscripcionesDAO;

    public GestorAlumnos() {
        this.alumnoDAO = new AlumnoDAO();
        this.inscripcionesDAO = new InscripcionesDAO();
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

    public Alumno buscarPorCI(String ci) throws AlumnoNoEncontradoException {
        Alumno existente = alumnoDAO.buscarPorCI(ci);
        if (existente == null) {
            throw new AlumnoNoEncontradoException();
        }
        return existente;
    }

    public List<Alumno> listarAlumnos() {
        return alumnoDAO.listarAlumnos();
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
        alumnoDAO.eliminarAlumno(ci);
    }

    public List<Alumno> listarPorEstadoAcademico(String estado) {
        return alumnoDAO.listarPorEstadoAcademico(estado);
    }

    public List<Alumno> buscarPorNombreApellido(String texto) {
        return alumnoDAO.buscarPorNombreApellido(texto);
    }

    //Aca usamos un metodo del DAO de inscipciones para facilitar.
    //Solo lo usamos en el caso donde al momento de eliminar un alumno tenemos que verificar si tiene inscripciones.
    public boolean tieneInscripciones(String ci) {
        Alumno alumno = alumnoDAO.buscarPorCI(ci);
        if (alumno == null) {
            return false;
        }
        return inscripcionesDAO.tieneInscripciones(alumno.getId());
    }
    
    public List<Materia> inscriptoMaterias(String ciAlumno){
        return inscripcionesDAO.inscriptoMaterias(ciAlumno);
    }
    
}
