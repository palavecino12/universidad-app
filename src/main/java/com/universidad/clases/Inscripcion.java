package com.universidad.clases;

public class Inscripcion {

    private int id;
    private Alumno alumno;
    private Materia materia;
    private String fechaInscripcion;

    //Constructor para crear una nueva inscripción.
    //No recibe id porque la db lo genera automáticamente.
    public Inscripcion(Alumno alumno, Materia materia, String fechaInscripcion) {
        this.alumno = alumno;
        this.materia = materia;
        this.fechaInscripcion = fechaInscripcion;
    }

    //Constructor para el momento traer una inscripcion de la db.
    //Este si recibe id porque lo trae de la db.
    public Inscripcion(int id, Alumno alumno, Materia materia, String fechaInscripcion) {
        this.id = id;
        this.alumno = alumno;
        this.materia = materia;
        this.fechaInscripcion = fechaInscripcion;
    }

    public int getId(){
        return this.id;
    }

    public Alumno getAlumno(){
        return this.alumno;
    }
    public void setAlumno(Alumno alumno){
        this.alumno = alumno;
    }

    public Materia getMateria(){
        return this.materia;
    }
    public void setMateria(Materia materia){
        this.materia = materia;
    }

    public String getFechaInscripcion(){
        return this.fechaInscripcion;
    }
    public void setFechaInscripcion(String fechaInscripcion){
        this.fechaInscripcion = fechaInscripcion;
    }
}
