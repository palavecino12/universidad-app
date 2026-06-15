package com.universidad.app;

import java.util.Scanner;

public class MenuPrincipal {

    Menu menu = new Menu();

    public void Iniciar() {
        Scanner leer = new Scanner(System.in);
        int opcion;
        System.out.println("===== SISTEMA UNIVERSITARIO =====\n"
                + "\n"
                + "1. Gestionar alumnos\n"
                + "2. Gestionar materias\n"
                + "3. Gestionar inscripciones\n"
                + "4. Gestionar calificaciones\n"
                + "5. Consultas\n"
                + "\n"
                + "0. Salir");

        opcion = leer.nextInt();

        switch(opcion){
            case 1:
                menu.menuAlumnos();
                break;
            case 2:
                menu.menuMaterias();
                break;
        }
        
    }
    
    
}
