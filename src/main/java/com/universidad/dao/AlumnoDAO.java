package com.universidad.dao;

import com.universidad.clases.Alumno;
import java.util.List;

public interface AlumnoDAO {

    void crearAlumno(Alumno alumno);

    Alumno buscarPorCI(String ci);

    Alumno buscarPorId(int id);

    List<Alumno> listarAlumnos();

    void modificarAlumno(Alumno alumno);

    void eliminarAlumno(int id);
}