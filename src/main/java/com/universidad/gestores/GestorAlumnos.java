package com.universidad.gestores;


import com.universidad.clases.Alumno;
import com.universidad.dao.AlumnoDAO;
import java.util.List;

public class GestorAlumnos {

      private final AlumnoDAO alumnoDAO;

    public GestorAlumnos(AlumnoDAO alumnoDAO) {
        this.alumnoDAO = alumnoDAO;
    }

    public void crearAlumno(Alumno alumno) {
        alumnoDAO.crearAlumno(alumno);
    }

    public Alumno buscarPorCI(String ci) {
        return alumnoDAO.buscarPorCI(ci);
    }

    public Alumno buscarPorId(int id) {
        return alumnoDAO.buscarPorId(id);
    }

    public List<Alumno> listarAlumnos() {
        return alumnoDAO.listarAlumnos();
    }

    public void modificarAlumno(Alumno alumno) {
        alumnoDAO.modificarAlumno(alumno);
    }

    public void eliminarAlumno(int id) {
        alumnoDAO.eliminarAlumno(id);
    }
}