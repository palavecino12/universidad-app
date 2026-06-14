package com.universidad.gestores;


import com.universidad.clases.Alumno;
import com.universidad.dao.AlumnoDAO;
import com.universidad.exceptions.AlumnoDuplicadoException;
import com.universidad.exceptions.AlumnoNoEncontradoException;
import java.util.List;
import java.util.Scanner;

public class GestorAlumnos {
    private final Scanner leer = new Scanner(System.in);    
    private final AlumnoDAO alumnoDAO;

    public GestorAlumnos(AlumnoDAO alumnoDAO) {
        this.alumnoDAO = alumnoDAO;
    }

    public void crearAlumno(Alumno alumno) throws AlumnoDuplicadoException {
        System.out.print("ingrese el nombre del alumno: ");
        alumno.setNombre(leer.next());
        System.out.print("ingrese el apellido de " + alumno.getNombre() + ": ");
        alumno.setApellido(leer.next());
        System.out.print("ingrese el CI: ");
        alumno.setCi(leer.next());
        if (alumnoDAO.buscarPorCI(alumno.getCi()) != null) {
            throw new AlumnoDuplicadoException();
        }
        System.out.print("ingrese el email: ");
        alumno.setEmail(leer.next());
        System.out.println(alumno.getNombre()+ " ingresado con exito!");
        alumnoDAO.crearAlumno(alumno);
    }

    public Alumno buscarPorCI(String ci) {
        return alumnoDAO.buscarPorCI(ci);
    }

    public List<Alumno> listarAlumnos() {
        List<Alumno> alumnos = alumnoDAO.listarAlumnos();
        for (Alumno alumno : alumnos) {
            System.out.println(alumno);
        }
        return alumnos;
    }

    public void modificarAlumno(Alumno alumno){
    int option;
    do {
        System.out.println("que desea modificar?");
        System.out.println("1= nombre y apellido");
        System.out.println("2= email");
        System.out.println("3= salir");

        option = leer.nextInt();

        switch(option){

            case 1:
                leer.nextLine();
                System.out.print("Ingrese el nombre: ");
                alumno.setNombre(leer.nextLine());

                System.out.print("Ingrese apellido: ");
                alumno.setApellido(leer.next());

                alumnoDAO.modificarAlumno(alumno);

                System.out.println("Nombre y apellido modificados");
                break;


            case 2:
                System.out.print("Ingrese email: ");
                alumno.setEmail(leer.next());

                alumnoDAO.modificarAlumno(alumno);

                System.out.println("Email modificado");
                break;


            case 3:
                System.out.println("Cerrando modificación");
                break;
        }

    } while(option != 3);
}

    public void eliminarAlumno(String ci) throws AlumnoNoEncontradoException {
        Alumno alumno = alumnoDAO.buscarPorCI(ci);
        if (alumno == null) {
            throw new AlumnoNoEncontradoException();
        }else if(alumno.getMateriasInscriptas() != null && !alumno.getMateriasInscriptas().isEmpty()){
            System.out.println("El alumno esta inscripto a materias");
            return;
        }
        System.out.println("Esta seguro que desea eliminar? s/n");
        if (leer.next().equalsIgnoreCase("s")) {
            alumnoDAO.eliminarAlumno(ci);
        }else{
            System.out.println("no se elimino ningun alumno");
        }    
    }
}