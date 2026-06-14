package com.universidad.app;

import com.universidad.clases.Alumno;
import com.universidad.dao.AlumnoDAO;
import com.universidad.exceptions.AlumnoDuplicadoException;
import com.universidad.gestores.GestorAlumnos;
import java.util.Scanner;

public class SistemGestion {

    public static void main(String[] args) throws AlumnoDuplicadoException {
        Scanner leer = new Scanner(System.in);

        AlumnoDAO alumnoDAO = new AlumnoDAO();

        GestorAlumnos gestorAlumnos = new GestorAlumnos(alumnoDAO);
        int opcion;
        do {

            System.out.println("\n--- SISTEMA UNIVERSIDAD ---");
            System.out.println("1. Gestionar alumnos");
            System.out.println("2. Gestionar materias");
            System.out.println("3. Inscripciones");
            System.out.println("4. Calificaciones");
            System.out.println("0. Salir");

            opcion = leer.nextInt();

            switch (opcion) {
                case 1:

                    menuAlumnos(gestorAlumnos);

                    break;

                case 0:

                    System.out.println("Cerrando sistema");

                    break;

                default:

                    System.out.println("Opcion incorrecta");

            }

        } while (opcion != 0);

    }

    public static void menuAlumnos(GestorAlumnos gestor) throws AlumnoDuplicadoException {

        Scanner leer = new Scanner(System.in);

        int opcion;

        do {

            System.out.println("\n--- ALUMNOS ---");
            System.out.println("1. Crear alumno");
            System.out.println("2. Buscar alumno");
            System.out.println("3. Listar alumnos");
            System.out.println("4. Modificar alumno");
            System.out.println("5. Eliminar alumno");
            System.out.println("0. Volver");

            opcion = leer.nextInt();
           

        } while (opcion != 0);
    }
}
