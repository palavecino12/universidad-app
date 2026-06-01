package com.universidad.app;

import com.universidad.clases.Docente;

public class TestHerencia {

    public static void main(String[] args) {

        Docente docente = new Docente();

        docente.setNombre("Facundo");
        docente.setApellido("Palavecino");
        docente.setCi("12345678");
        docente.setEspecialidad("Programacion");

        docente.mostrarInfo();

    }

}