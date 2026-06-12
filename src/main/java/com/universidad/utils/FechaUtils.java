/**
 * Métodos utilitarios relacionados con fechas.
 * Por el momento solo un metodo que retorna la fecha actual.
 */
package com.universidad.utils;

import java.time.LocalDate;

public class FechaUtils {

        //Metodo que retorna la fecha actual.
        public static String obtenerFechaActual() {
            return LocalDate.now().toString();
        }
}
