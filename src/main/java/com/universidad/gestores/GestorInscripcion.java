package com.universidad.gestores;

import com.universidad.clases.Alumno;
import com.universidad.clases.Inscripcion;
import com.universidad.clases.Materia;
import com.universidad.dao.InscripcionesDAO;

import java.time.LocalDate;
import java.util.List;

public class GestorInscripcion {

    private final InscripcionesDAO inscripcionDAO;

    public GestorInscripcion(InscripcionesDAO inscripcionesDAO) {

        this.inscripcionDAO = inscripcionesDAO;

    }

    public void inscribirAlumno(Alumno alumno, Materia materia) {
        
    }
