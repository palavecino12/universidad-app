/**
 * Clase utilitaria para leer y validar datos ingresados por consola.
 */
package com.universidad.utils;

import java.util.Scanner;

public class InputHelper {

    //Colocamos un Scanner statis para que haya uno solo en toda la app.
    private static final Scanner scanner = new Scanner(System.in);

    //Metodo para leer texto por consola.
    public static String leerTexto() {

        while (true) {

            String texto = scanner.nextLine();

            //Verificamos que el texto sea solo letras y/o espacios.
            if (texto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                return texto;
            }

            System.out.println(
                    "Ingrese solo letras."
            );
        }
    }

    //Metodo para leer enteros por consola.
    public static int leerEntero() {

        while (true) { //Bucle infinito, la unica salida es el return si no hay errores.

            try {
                //Leemos texto y lo transformamos en un entero. Por el problema del enter.
                String texto = scanner.nextLine();
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido:");
            }
        }
    }
}
