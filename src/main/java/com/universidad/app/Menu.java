package com.universidad.app;

import com.universidad.clases.Alumno;
import com.universidad.clases.Calificacion;
import com.universidad.clases.Materia;
import com.universidad.exceptions.AlumnoNoEncontradoException;
import com.universidad.exceptions.MateriaNoEncontradaException;
import com.universidad.gestores.GestorAlumnos;
import com.universidad.gestores.GestorCalificaciones;
import com.universidad.gestores.GestorMaterias;
import java.time.LocalDate;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner leer = new Scanner(System.in);
    int option;
    GestorAlumnos gestorAlumnos = new GestorAlumnos();
    GestorMaterias gestorMaterias = new GestorMaterias();
    GestorCalificaciones gestorCalificaciones = new GestorCalificaciones();

    public void menuAlumnos() {

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

                    Materia materia = new Materia(nombre, codigo, cupo);

                    gestorMaterias.registrarMateria(materia);
                    break;

                case 2:
                    System.out.print("Codigo de la materia: ");

                    String codigoModificar
                            = leer.next();

                    Materia modificar
                            = gestorMaterias.buscarPorCodigo(
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

                    String codigoEliminar
                            = leer.next();

                    gestorMaterias.eliminarMateria(
                            codigoEliminar
                    );
                    break;

                case 4:
                    List<Materia> materias
                            = gestorMaterias.listarMaterias();

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

    public void menuCalificaciones() throws AlumnoNoEncontradoException, MateriaNoEncontradaException {
        System.out.println("\n--- MENU CALIFICACIONES ---");
        System.out.println("1. cargar nota");
        System.out.println("2. Modificar nota");
        System.out.println("3. Eliminar nota");
        System.out.println("4. ver notas de un alumno");
        System.out.println("0. Volver");
        option = leer.nextInt();

        switch (option) {
            case 1:
                System.out.print("Ingrese el CI del alumno: ");
                Alumno a = gestorAlumnos.buscarPorCI(leer.next());
                if (a == null) {
                    throw new AlumnoNoEncontradoException();
                }
                System.out.print("Ingrese el codigo de la materia: ");
                Materia m = gestorMaterias.buscarPorCodigo(leer.next());
                if (m == null) {
                    throw new MateriaNoEncontradaException();
                }
                if (!a.estaInscripto(m)) {
                    System.out.println(
                            "El alumno no esta inscripto en esa materia."
                    );
                    break;
                }

                System.out.print("Ingrese la nota: ");
                double nota = leer.nextDouble();
                String fecha = LocalDate.now().toString();
                Calificacion c = new Calificacion(nota, fecha, m);
                gestorCalificaciones.cargarNota(c);
                break;

            case 2:
                System.out.print("Ingrese el ID de la calificacion: ");
                int id = leer.nextInt();
                Calificacion c1 = gestorCalificaciones.buscarPorId(id);
                if (c1 == null) {
                    System.out.println("No existe esa calificacion.");
                    break;
                }
                System.out.print("Ingrese la nueva nota: ");
                double nuevaNota = leer.nextDouble();
                gestorCalificaciones.modificarNota(c1);
                break;

            case 3:

                System.out.print("Ingrese el ID de la nota a eliminar: ");
                int i = leer.nextInt();
                System.out.println("¿Esta seguro que desea eliminar la nota? s/n");
                String respuesta = leer.next();
                if (respuesta.equalsIgnoreCase("s")) {
                    gestorCalificaciones.eliminarNota(i);
                } else {
                    System.out.println("No se elimino ninguna nota.");
                }
                break;

            case 4:
                System.out.print("Ingrese el CI del alumno: ");
                String ci = leer.next();
                Alumno alumno = gestorAlumnos.buscarPorCI(ci);
                if (alumno == null) {
                    System.out.println("Alumno no encontrado");
                    break;
                }
                System.out.println("===== NOTAS DEL ALUMNO =====");
                for (Materia materia : alumno.getMateriasInscriptas()) {
                    System.out.println("\nMateria: " + materia.getNombre());
                    boolean tieneNota = false;
                    for (Calificacion calificacion : alumno.getCalificaciones()) {
                        if (calificacion.getMateria().getCodigo().equals(materia.getCodigo())) {
                            System.out.println("Nota: " + calificacion.getNota());
                            tieneNota = true;
                        }
                    }
                    if (!tieneNota) {
                        System.out.println("Sin calificaciones registradas");
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
