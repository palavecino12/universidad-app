package com.universidad.app;

import com.universidad.clases.Alumno;
import com.universidad.clases.Calificacion;
import com.universidad.clases.Materia;
import com.universidad.exceptions.AlumnoDuplicadoException;
import com.universidad.exceptions.AlumnoNoEncontradoException;
import com.universidad.exceptions.MateriaNoEncontradaException;
import com.universidad.gestores.GestorAlumnos;
import com.universidad.gestores.GestorCalificaciones;
import com.universidad.gestores.GestorInscripcion;
import com.universidad.gestores.GestorMaterias;
import java.time.LocalDate;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner leer = new Scanner(System.in);
    int option;
    GestorAlumnos gestorAlumnos = new GestorAlumnos();
    GestorMaterias gestorMaterias = new GestorMaterias();
    GestorInscripcion gestorInscripcion = new GestorInscripcion();
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
                    try {
                        gestorAlumnos.registrarAlumno(alumno);
                    } catch (AlumnoDuplicadoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("CI del alumno: ");
                    String ciModificar = leer.next();
                    Alumno modificar = null;
                    try {
                        modificar = gestorAlumnos.buscarPorCI(ciModificar);
                    } catch (AlumnoNoEncontradoException ex) {
                        System.out.println(ex.getMessage());
                    }
                    if (modificar != null) {
                        System.out.print("Nuevo Nombre: ");
                        modificar.setNombre(leer.next());
                        System.out.print("Nuevo Apellido: ");
                        modificar.setApellido(leer.next());
                        System.out.print("Nuevo Email: ");
                        modificar.setEmail(leer.next());
                        try {
                            gestorAlumnos.modificarAlumno(modificar);
                        } catch (AlumnoNoEncontradoException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Alumno no encontrado");
                    }

                    break;

                case 3:
                    System.out.print("CI: ");
                    String ciEliminar = leer.next();
                    if (gestorAlumnos.tieneInscripciones(ciEliminar)) {

                        System.out.println(
                                "El alumno posee inscripciones."
                        );

                        System.out.print(
                                "¿Desea eliminarlo igualmente? (S/N): "
                        );

                        String respuesta = leer.next();

                        if (!respuesta.equalsIgnoreCase("S")) {

                            System.out.println(
                                    "Operación cancelada."
                            );

                            break;
                        }
                    }

                    try {
                        gestorAlumnos.eliminarAlumno(
                                ciEliminar
                        );

                    } catch (AlumnoNoEncontradoException e) {
                        System.out.println(e.getMessage());

                    }
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

                    Materia modificar = null;
                    try {
                        modificar = gestorMaterias.buscarPorCodigo(
                                codigoModificar
                        );
                    } catch (MateriaNoEncontradaException ex) {
                        System.out.println(ex.getMessage());
                    }

                    if (modificar != null) {

                        leer.nextLine();

                        System.out.print("Nuevo nombre: ");
                        modificar.setNombre(
                                leer.nextLine()
                        );

                        System.out.print("Nuevo cupo maximo: ");
                        modificar.setCupoMaximo(leer.nextInt());

                        try {
                            gestorMaterias.modificarMateria(modificar);
                        } catch (MateriaNoEncontradaException e) {
                            System.out.println(e.getMessage());
                        }

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

                    try {
                        gestorMaterias.eliminarMateria(codigoEliminar);
                    } catch (MateriaNoEncontradaException e) {
                        System.out.println(e.getMessage());
                    }
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
        do {

            System.out.println("\n--- MENU INSCRIPCIONES ---");
            System.out.println("1. Inscribir alumno");
            System.out.println("2. Dar de baja alumno");
            System.out.println("3. Listar alumnos inscriptos");
            System.out.println("0. Volver");

            option = leer.nextInt();

            switch (option) {

                case 1:
                    System.out.print("CI del alumno: ");
                    String ci = leer.next();

                    System.out.print("Código de la materia: ");
                    String codigo = leer.next();

                    Alumno alumno = null;
                    try {
                        alumno = gestorAlumnos.buscarPorCI(ci);
                    } catch (AlumnoNoEncontradoException ex) {
                        System.out.println(ex.getMessage());
                    }
                    Materia materia = null;
                    try {
                        materia = gestorMaterias.buscarPorCodigo(codigo);
                    } catch (MateriaNoEncontradaException ex) {
                        System.out.println(ex.getMessage());
                    }

                    if (alumno == null) {

                        System.out.println(
                                "Alumno no encontrado."
                        );

                        break;
                    }

                    if (materia == null) {

                        System.out.println(
                                "Materia no encontrada."
                        );

                        break;
                    }
                    gestorInscripcion.inscribirAlumno(alumno, materia);
                    break;

                case 2:
                    System.out.print("CI del alumno: ");
                    String ciBaja = leer.next();

                    System.out.print("Código de materia: ");
                    String codigoBaja = leer.next();

                    Alumno alumnoBaja = null;
                    try {
                        alumnoBaja = gestorAlumnos.buscarPorCI(ciBaja);
                    } catch (AlumnoNoEncontradoException ex) {
                        System.out.println(ex.getMessage());
                    }

                    Materia materiaBaja = null;
                    try {
                        materiaBaja = gestorMaterias.buscarPorCodigo(codigoBaja);
                    } catch (MateriaNoEncontradaException ex) {
                        System.out.println(ex.getMessage());
                    }

                    if (alumnoBaja == null) {

                        System.out.println(
                                "Alumno no encontrado."
                        );

                        break;
                    }

                    if (materiaBaja == null) {

                        System.out.println(
                                "Materia no encontrada."
                        );

                        break;
                    }

                    System.out.print(
                            "¿Confirma la baja? (S/N): "
                    );

                    String respuesta = leer.next();

                    if (respuesta.equalsIgnoreCase("S")) {

                        gestorInscripcion.darDeBajaAlumno(
                                alumnoBaja,
                                materiaBaja
                        );

                    } else {

                        System.out.println(
                                "Operación cancelada."
                        );

                    }
                    break;

                case 3:
                    System.out.print(
                            "Código de materia: "
                    );

                    String codigoMateria
                            = leer.next();

                    Materia materiaConsulta = null;
                    try {
                        materiaConsulta = gestorMaterias.buscarPorCodigo(codigoMateria);
                    } catch (MateriaNoEncontradaException ex) {
                        System.out.println(ex.getMessage());
                    }

                    if (materiaConsulta == null) {

                        System.out.println(
                                "Materia no encontrada."
                        );

                        break;
                    }

                    gestorInscripcion
                            .listarAlumnosInscriptos(
                                    materiaConsulta
                            );
                    break;

                case 0:
                    System.out.println("Volviendo...");
                    break;

                default:
                    System.out.println("Opción incorrecta");
            }

        } while (option != 0);
    }

    public void menuCalificaciones() {
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
                Alumno a = null;
                Materia m = null;
                try {
                    a = gestorAlumnos.buscarPorCI(leer.next());
                } catch (AlumnoNoEncontradoException e) {
                    System.out.println(e.getMessage());
                    break;
                }
                System.out.print("Ingrese el codigo de la materia: ");
                try {
                    m = gestorMaterias.buscarPorCodigo(leer.next());
                } catch (MateriaNoEncontradaException e) {
                    System.out.println(e.getMessage());
                    break;
                }
                if (!gestorInscripcion.estaInscripto(a, m)) {
                    System.out.println("El alumno no esta inscripto en esa materia.");
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
                Alumno alumno = null;
                try {
                    alumno = gestorAlumnos.buscarPorCI(ci);
                } catch (AlumnoNoEncontradoException e) {
                    System.out.println(e.getMessage());
                }
                if (alumno == null) {
                    System.out.println("Alumno no encontrado");
                    break;
                }
                System.out.println("===== NOTAS DEL ALUMNO =====");

                List<Calificacion> notas = gestorCalificaciones.listarPorAlumno(ci);

                if (notas.isEmpty()) {
                    System.out.println("Sin calificaciones registradas");
                    break;
                }

                for (Calificacion calificacion : notas) {
                    System.out.println(
                            "Materia: "
                            + calificacion.getMateria().getNombre()
                            + " | Nota: "
                            + calificacion.getNota()
                    );
                }
                break;

            case 0:
                System.out.println("Volviendo...");
                break;

            default:
                System.out.println("Opcion incorrecta");
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
                try {
                    System.out.println(ga.buscarPorCI(codigo));
                } catch (AlumnoNoEncontradoException ex) {
                    System.out.println(ex.getMessage());
                }
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
