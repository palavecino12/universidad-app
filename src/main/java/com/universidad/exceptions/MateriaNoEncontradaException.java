package com.universidad.exceptions;

public class MateriaNoEncontradaException extends Exception{

    public MateriaNoEncontradaException() {
        super("No se ha encontrado esta materia.");
    }

}
