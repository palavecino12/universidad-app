package com.universidad.gestores;

import com.universidad.clases.Materia;
import com.universidad.dao.MateriaDAO;
import java.util.List;
import java.util.Scanner;

public class GestorMaterias {
     private final MateriaDAO materiaDAO;

    public GestorMaterias(MateriaDAO materiaDAO) {
        this.materiaDAO = materiaDAO;
    }
    
    public void crearMateria(Materia materia) {
        Scanner leer = new Scanner(System.in);
        System.out.print("ingrese el nombre de la materia: ");
        materia.setNombre(leer.next());
        System.out.print("ingrese el codigo de " + materia.getNombre() + ": ");
        materia.setCodigo(leer.next());
        System.out.print("ingrese el cupo maximo: ");
        materia.setCupoMaximo(leer.nextInt());
        do {
            if (materiaDAO.buscarPorCodigo(materia.getCodigo()) != null) {
                System.out.println("Código duplicado");
                System.out.print("Ingrese un nuevo código: ");
                materia.setCodigo(leer.next());
            }
        } while (materiaDAO.buscarPorCodigo(materia.getCodigo()) != null);
        materiaDAO.crearMateria(materia);
        System.out.println(materia.getNombre()+ " creada con exito!");
    }

    public List<Materia> listarMaterias() {
        List<Materia> materias = materiaDAO.listarMaterias();
        for (Materia materia : materias) {
            System.out.println(materia);
        }

    return materias;
}

    public Materia buscarPorCodigo(String codigo) {
        return materiaDAO.buscarPorCodigo(codigo);
    }
    
    public void modificarMateria(Materia materia){
        Scanner leer = new Scanner(System.in);
        int option;
        boolean b = true;
        do {
            System.out.println("que desea modificar? ");
            System.out.println("1= nombre");
            System.out.println("2= codigo");
            System.out.println("3= cupo maximo");
            System.out.println("4= docente");
            System.out.println("5= salir");
          
            switch (option=leer.nextInt()){
                case 1: 
                    System.out.print("ingrese el nombre de la materia: ");
                    materia.setNombre(leer.nextLine());
                    break;
                case 2:
                    System.out.print("ingrese el codigo de " + materia.getNombre() + ": ");
                    materia.setCodigo(leer.next());
                    do {
                        if (materiaDAO.buscarPorCodigo(materia.getCodigo()) != null) {
                            System.out.println("Código duplicado");
                            System.out.print("Ingrese un nuevo código: ");
                            materia.setCodigo(leer.next());
                        }
                    } while (materiaDAO.buscarPorCodigo(materia.getCodigo()) != null);
                    break;
                case 3:
                    System.out.print("ingrese el cupo maximo: ");
                    materia.setCupoMaximo(leer.nextInt());
                    break;
                case 4:
                    System.out.println("por el momento no hay docente");
                    break;
                case 5: 
                    System.out.println("cerrando modificacion");
                    b=false;
                    break;
            }
            
        } while (b);
        materiaDAO.modificarMateria(materia);
    }
    
    public void eliminarMateria(){
        Scanner leer = new Scanner(System.in);
        System.out.print("ingrese el codigo de la materia que va a eliminar: ");
        materiaDAO.eliminarMateria(leer.nextLine());
    }
}
