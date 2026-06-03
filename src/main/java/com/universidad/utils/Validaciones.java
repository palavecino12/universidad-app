package com.universidad.utils;

public class Validaciones {

    public static boolean esTextoValido(String texto) {
        //En caso de que el nombre solo contenga letra retorna true.
        return texto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+");

    }

    public static boolean noEstaVacio(String texto) {
        //Retorna true si existe un texto y además no está vacío.
        return texto != null && !texto.trim().isEmpty();

    }

    public static boolean esEmailValido(String email) {
        //Retorna true si el email contiene el arroba y el punto.
        return email.contains("@") && email.contains(".");

    }

    public static boolean esNotaValida(double nota) {
        //Retorna true si la nota esta entre 1 y 12.
        return nota >= 1 && nota <= 12;

    }
}
