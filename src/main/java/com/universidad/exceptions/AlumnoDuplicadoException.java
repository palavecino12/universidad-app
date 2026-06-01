package com.universidad.exceptions;

public class AlumnoDuplicadoException extends Exception {

    public AlumnoDuplicadoException() {
        super("Ya existe un alumno con esa CI.");
    }

}