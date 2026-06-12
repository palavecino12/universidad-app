package com.universidad.clases;

public class Materia {

    private String nombre;
    private String codigo;
    private int cupoMaximo;
    private Docente docente;

    public Materia() {
    }

    public Materia(String nombre, String codigo, int cupoMaximo, Docente docente) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.cupoMaximo = cupoMaximo;
        this.docente = docente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    

    @Override
    public String toString() {
        return nombre + " (" + codigo + ")";
    }
}