package com.universidad.gestores;

import com.universidad.clases.Alumno;
import com.universidad.clases.Inscripcion;
import com.universidad.clases.Materia;
import com.universidad.dao.InscripcionesDAO;
import com.universidad.dao.MateriaDAO;
import com.universidad.exceptions.MateriaNoEncontradaException;

import java.time.LocalDate;
import java.util.List;

public class GestorInscripcion {

    private InscripcionesDAO inscripcionDAO;

    public GestorInscripcion() {
        this.inscripcionDAO = new InscripcionesDAO();
    }

    public Inscripcion buscarInscripcion(Alumno a, Materia m) {
        Inscripcion i = inscripcionDAO.buscarInscripcion(a.getCi(), m.getCodigo());
        if (i == null) {
            System.out.println("La inscripcion no existe");
        }
        return i;
    }

    public boolean validarCalificacion(String ci, String codigoMateria){
        if (inscripcionDAO.tieneCalificacion(ci, codigoMateria)) {
            return true;
        }
        return false;
    }

    public boolean validarDuplicado(Alumno alumno, Materia materia) {

        Inscripcion inscripcion = inscripcionDAO.buscarInscripcion(alumno.getCi(), materia.getCodigo());

        return inscripcion != null;
    }

    public boolean validarCupo(Materia materia) {

        List<Inscripcion> inscripciones
                = inscripcionDAO.listarPorMateria(
                        materia.getCodigo()
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

        Inscripcion inscripcion = new Inscripcion(alumno, materia, LocalDate.now().toString());

        inscripcionDAO.crearInscripcion(inscripcion);

        System.out.println(
                "Inscripción realizada correctamente."
        );
    }

    public void darDeBajaAlumno(Alumno alumno, Materia materia) {

        Inscripcion inscripcion
                = inscripcionDAO.buscarInscripcion(
                        alumno.getCi(),
                        materia.getCodigo()
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

        List<Inscripcion> inscripciones
                = inscripcionDAO.listarPorMateria(
                        materia.getCodigo()
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

            Alumno alumno
                    = inscripcion.getAlumno();

            System.out.println(
                    alumno.getNombre()
                    + " "
                    + alumno.getApellido()
                    + " - CI: "
                    + alumno.getCi()
            );
        }
    }

    public boolean estaInscripto(Alumno a, Materia m) {

        return inscripcionDAO.existeInscripcion(
                a.getId(),
                m.getId()
        );

    }

    public int cantidadInscriptosEnMateria(String codigo) throws MateriaNoEncontradaException {
        // 1. Validaciones opcionales (por ejemplo, verificar si el id > 0)
        MateriaDAO md = new MateriaDAO();
        Materia m = md.buscarPorCodigo(codigo);
        if (m == null) {
            throw new MateriaNoEncontradaException();
        }

        // 2. Delegamos la lógica al DAO
        return inscripcionDAO.contarInscriptosPorMateria(codigo);
    }
}
