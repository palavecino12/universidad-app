package com.universidad.app;

import com.universidad.clases.Alumno;
import com.universidad.dao.AlumnoDAO;
import com.universidad.exceptions.AlumnoDuplicadoException;
import com.universidad.gestores.GestorAlumnos;
import java.util.Scanner;

public class SistemGestion {

    public static void main(String[] args) {
        MenuPrincipal menu = new MenuPrincipal();

        menu.Iniciar();;

    }
}
