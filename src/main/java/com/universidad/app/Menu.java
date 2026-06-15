package com.universidad.app;

import com.universidad.clases.Alumno;
import com.universidad.clases.Materia;
import com.universidad.gestores.GestorAlumnos;
import com.universidad.gestores.GestorMaterias;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner leer = new Scanner(System.in);
    int option;

    public void menuAlumnos() {
        GestorAlumnos gestorAlumnos = new GestorAlumnos();
        do {

            System.out.println("\n--- MENU ALUMNOS ---");
            System.out.println("1. Registrar alumno");
            System.out.println("2. Modificar alumno");
            System.out.println("3. Eliminar alumno");
            System.out.println("4. Listar alumnos");
            System.out.println("0. Volver");

            option = leer.nextInt();

            switch (option) {

                case 1:
                    leer.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = leer.nextLine();
                    System.out.print("Apellido: ");
                    String apellido = leer.nextLine();
                    System.out.print("CI: ");
                    String ci = leer.nextLine();
                    System.out.print("Fecha nacimiento: ");
                    String fechaNacimiento = leer.nextLine();
                    System.out.print("Email: ");
                    String email = leer.nextLine();
                    Alumno alumno = new Alumno(nombre, apellido, ci, fechaNacimiento, email);
                    gestorAlumnos.registrarAlumno(alumno);
                    break;

                case 2:
                    System.out.print("CI del alumno: ");
                    String ciModificar = leer.next();
                    Alumno modificar = gestorAlumnos.buscarPorCI(ciModificar);
                    if (modificar != null) {
                        System.out.print("Nuevo Nombre: ");
                        modificar.setNombre(leer.next());
                        System.out.print("Nuevo Apellido: ");
                        modificar.setApellido(leer.next());
                        System.out.print("Nuevo Email: ");
                        modificar.setEmail(leer.next());
                        gestorAlumnos.modificarAlumno(modificar);
                    } else {
                        System.out.println("Alumno no encontrado");
                    }

                    break;
                case 3:
                    System.out.print("CI: ");
                    String ciEliminar = leer.next();
                    gestorAlumnos.eliminarAlumno(ciEliminar);
                    break;

                case 4:
                    List<Alumno> alumnos = gestorAlumnos.listarAlumnos();
                    if (alumnos.isEmpty()) {
                        System.out.println("No hay alumnos registrados.");
                    } else {
                        System.out.println("--- LISTA DE ALUMNOS ---");
                        for (Alumno a : alumnos) {
                            System.out.println(a);
                        }
                    }
                    break;

                case 0:
                    System.out.println("Volviendo...");
                    break;

                default:
                    System.out.println(
                            "Opcion incorrecta"
                    );

            }

        } while (option != 0);

    }

    public void menuMaterias() {

        GestorMaterias gestorMaterias = new GestorMaterias();

        do {

            System.out.println("\n--- MENU MATERIAS ---");
            System.out.println("1. Registrar materia");
            System.out.println("2. Modificar materia");
            System.out.println("3. Eliminar materia");
            System.out.println("4. Listar materias");
            System.out.println("0. Volver");

            option = leer.nextInt();

            switch (option) {

                case 1:
                    leer.nextLine();

                    System.out.print("Nombre: ");
                    String nombre = leer.nextLine();

                    System.out.print("Codigo: ");
                    String codigo = leer.nextLine();

                    System.out.print("Cupo maximo: ");
                    int cupo = leer.nextInt();

                    Materia materia = new Materia(nombre,codigo,cupo);

                    gestorMaterias.registrarMateria(materia);
                    break;

                case 2:
                    System.out.print("Codigo de la materia: ");

                    String codigoModificar =
                            leer.next();

                    Materia modificar =
                            gestorMaterias.buscarPorCodigo(
                                    codigoModificar
                            );

                    if (modificar != null) {

                        leer.nextLine();

                        System.out.print("Nuevo nombre: ");
                        modificar.setNombre(
                                leer.nextLine()
                        );

                        System.out.print("Nuevo cupo maximo: ");
                        modificar.setCupoMaximo(
                                leer.nextInt()
                        );

                        gestorMaterias.modificarMateria(
                                modificar
                        );

                    } else {

                        System.out.println(
                                "Materia no encontrada"
                        );

                    }

                    break;

                case 3:
                    System.out.print("Codigo: ");

                    String codigoEliminar =
                            leer.next();

                    gestorMaterias.eliminarMateria(
                            codigoEliminar
                    );
                    break;

                case 4:
                    List<Materia> materias =
                            gestorMaterias.listarMaterias();

                    if (materias.isEmpty()) {

                        System.out.println(
                                "No hay materias registradas."
                        );

                    } else {

                        System.out.println(
                                "--- LISTA DE MATERIAS ---"
                        );

                        for (Materia m : materias) {

                            System.out.println(m);

                        }

                    }
                    break;

                case 0:
                    System.out.println("Volviendo...");
                    break;

                default:
                    System.out.println("Opcion incorrecta");
            }

        } while (option != 0);
    }

    public void menuInscripciones() {
    }

    public void menuCalificaciones() {
    }

    public void menuConsultas() {
        GestorAlumnos ga = new GestorAlumnos();
        System.out.println("1= buscar alumno por CI");
        System.out.println("2= buscar alumno por Nombre o apellido");
        System.out.println("3= buscar alumno por condicion");
        System.out.println("0= volver");
        option = leer.nextInt();

        switch (option) {
            case 1:
                System.out.print("ingrese el CI del alumno: ");
                String codigo = leer.next();
                System.out.println(ga.buscarPorCI(codigo));
                break;

            case 2:
                System.out.print("ingrese el nombre o el apellido: ");
                String nom = leer.next();
                List<Alumno> resultados = ga.buscarPorNombreApellido(nom);
                if (resultados.isEmpty()) {
                    System.out.println("No se encontraron alumnos");
                } else {
                    for (Alumno alumno : resultados) {
                        System.out.println(alumno);
                    }
                }
                break;

            case 3:
                System.out.println("1. Aprobado");
                System.out.println("2. Desaprobado");
                System.out.println("3. Sin calificación");
                int filtro = leer.nextInt();
                String estado = "";
                switch (filtro) {
                    case 1:
                        estado = "aprobado";
                        break;
                    case 2:
                        estado = "desaprobado";
                        break;
                    case 3:
                        estado = "sin calificacion";
                        break;
                }

                List<Alumno> lista = ga.listarPorEstadoAcademico(estado);

                for (Alumno alumno : lista) {
                    System.out.println(alumno);
                }
                break;

            case 0:
                System.out.println("Volviendo...");
                break;

            default:
                System.out.println(
                        "Opcion incorrecta"
                );

        }
    }

}
