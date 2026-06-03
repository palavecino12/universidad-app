/**
 * En la consiga, en el listado de clases tenemos:
 * Calificacion con el atributo "materia" mas el resto
 * pero en la base de datos tenemos que calificacion se relaciona con inscripcion
 * por lo tanto calificacion llevaria como atributo inscripcion y no materia
 * ademas se puede sacar materia de inscripcion
 */
package com.universidad.clases;

public class Calificacion {

    private int id;
    private double nota;
    private String fecha;
    private Inscripcion inscripcion;

    //Constructor para crear una nueva calificaion.
    //No recibe id porque la db lo genera automáticamente.
    public Calificacion(double nota, String fecha, Inscripcion inscripcion) {
        this.nota = nota;
        this.fecha = fecha;
        this.inscripcion = inscripcion;
    }

    //Constructor para el momento traer una calificacion de la db.
    //Este si recibe id porque lo trae de la db.
    public Calificacion(int id, double nota, String fecha, Inscripcion inscripcion) {
        this.id = id;
        this.nota = nota;
        this.fecha = fecha;
        this.inscripcion = inscripcion;
    }

    public boolean esAprobado() {
        return nota >= 6;
    }

    public int getId(){
        return this.id;
    }

    public double getNota(){
        return this.nota;
    }
    public void setNota(double nota){
        this.nota=nota;
    }

    public String getFecha(){
        return this.fecha;
    }
    public void setFecha(String fecha){
        this.fecha=fecha;
    }

    public Inscripcion getInscripcion(){
        return this.inscripcion;
    }
    public void setInscripcion(Inscripcion inscripcion){
        this.inscripcion=inscripcion;
    }
}
