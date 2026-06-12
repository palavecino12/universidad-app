package com.universidad.exceptions;

public class CupoExcedidoException extends Exception {

    public CupoExcedidoException() {
        super("Ya no hay cupos disponibles para esta materia.");
    }

}
