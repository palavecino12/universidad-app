package com.universidad.app;

import com.universidad.utils.InputHelper;
public class TestImputHelper {
    public static void main(String[] args) {

        System.out.println("Ingrese su nombre:");
        String nombre = InputHelper.leerTexto();

        System.out.println("Ingrese su edad:");
        int edad = InputHelper.leerEntero();

        System.out.println("Nombre: " + nombre);
        System.out.println("Edad: " + edad);
    }
}
