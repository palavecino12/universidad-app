package com.universidad.clases;

public class Calificacion {

    private int id;
    private double nota;
    private String fecha;
    private Materia materia;

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
    //agregue alumno porque en el uml sale y en los constructores igual
    private Alumno alumno;

    //Constructor para crear una nueva calificacion.
    //No recibe id porque la db lo genera automáticamente.
    public Calificacion(double nota, String fecha, Materia materia, Alumno alumno) {
        this.nota = nota;
        this.fecha = fecha;
        this.materia = materia;
        this.alumno = alumno;
    }

    //Constructor para el momento traer una calificacion de la db.
    //Este si recibe id porque lo trae de la db.
    public Calificacion(int id, double nota, String fecha, Materia materia, Alumno alumno) {
        this.id = id;
        this.nota = nota;
        this.fecha = fecha;
        this.materia = materia;
        this.alumno = alumno;
    }

    public boolean esAprobado() {
        return nota >= 6;
    }

    public int getId() {
        return this.id;
    }

    public double getNota() {
        return this.nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getFecha() {
        return this.fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Materia getMateria() {
        return this.materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}
