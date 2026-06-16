package com.universidad.gestores;

import com.universidad.clases.Alumno;
import com.universidad.clases.Inscripcion;
import com.universidad.clases.Materia;
import com.universidad.dao.InscripcionesDAO;

import java.time.LocalDate;
import java.util.List;

public class GestorInscripcion {

    private InscripcionesDAO inscripcionDAO;

    public GestorInscripcion() {
        this.inscripcionDAO = new InscripcionesDAO();
    }

    public boolean validarDuplicado(Alumno alumno, Materia materia) {

        Inscripcion inscripcion = inscripcionDAO.buscarInscripcion(alumno.getId(), materia.getId());

        return inscripcion != null;
    }

    public boolean validarCupo(Materia materia) {

        List<Inscripcion> inscripciones =
                inscripcionDAO.listarPorMateria(
                        materia.getId()
                );

        return inscripciones.size()
                < materia.getCupoMaximo();
    }

    public void inscribirAlumno(Alumno alumno, Materia materia) {

        if (validarDuplicado(alumno, materia)) {

            System.out.println(
                    "El alumno ya está inscripto en esta materia."
            );

            return;
        }

        if (!validarCupo(materia)) {

            System.out.println(
                    "No hay cupos disponibles."
            );

            return;
        }

        Inscripcion inscripcion =
                new Inscripcion(
                        alumno,
                        materia,
                        LocalDate.now().toString()
                );

        inscripcionDAO.crearInscripcion(inscripcion);

        System.out.println(
                "Inscripción realizada correctamente."
        );
    }

    public void darDeBajaAlumno(Alumno alumno, Materia materia) {

        Inscripcion inscripcion =
                inscripcionDAO.buscarInscripcion(
                        alumno.getId(),
                        materia.getId()
                );

        if (inscripcion == null) {

            System.out.println(
                    "El alumno no está inscripto en esa materia."
            );

            return;
        }

        inscripcionDAO.eliminarInscripcion(
                alumno.getId(),
                materia.getId()
        );

        System.out.println(
                "Baja realizada correctamente."
        );
    }

    public void listarAlumnosInscriptos(Materia materia) {

        List<Inscripcion> inscripciones =
                inscripcionDAO.listarPorMateria(
                        materia.getId()
                );

        if (inscripciones.isEmpty()) {

            System.out.println(
                    "No hay alumnos inscriptos en esta materia."
            );

            return;
        }

        System.out.println(
                "\n--- ALUMNOS INSCRIPTOS EN "
                        + materia.getNombre()
                        + " ---"
        );

        for (Inscripcion inscripcion : inscripciones) {

            Alumno alumno =
                    inscripcion.getAlumno();

            System.out.println(
                    alumno.getNombre()
                            + " "
                            + alumno.getApellido()
                            + " - CI: "
                            + alumno.getCi()
            );
        }
    }
}