package com.universidad.gestores;

import com.universidad.clases.Alumno;
import com.universidad.clases.Inscripcion;
import com.universidad.clases.Materia;
import com.universidad.dao.InscripcionesDAO;

import java.time.LocalDate;
import java.util.List;


public class GestorInscripcion{


    private final InscripcionesDAO inscripcionDAO;


    public GestorInscripcion(InscripcionesDAO inscripcionesDAO){

        this.inscripcionDAO = inscripcionesDAO;

    }



    public void inscribirAlumno(Alumno alumno, Materia materia){


        if(materia.tieneCupo()){


            String fecha =
                    LocalDate.now().toString();


            Inscripcion inscripcion =
                    new Inscripcion(
                            alumno,
                            materia,
                            fecha
                    );


            inscripcionDAO.crearInscripcion(inscripcion);


            alumno.getMateriasInscriptas()
                    .add(materia);


            materia.getAlumnosInscriptos()
                    .add(alumno);



            System.out.println(
                    "Inscripcion realizada correctamente"
            );


        }else{


            System.out.println(
                    "No hay cupos disponibles"
            );

        }

    }



    public List<Inscripcion> listarInscripciones(){

        return inscripcionDAO.listarInscripciones();

    }


}