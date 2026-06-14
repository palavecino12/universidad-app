package com.universidad.clases;

import java.util.ArrayList;

public class Materia {

    private String nombre;
    private String codigo;
    private int cupoMaximo;
    private ArrayList<Alumno> alumnosInscriptos;

    public Materia(String nombre, String codigo, int cupoMaximo) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.cupoMaximo = cupoMaximo;
        this.alumnosInscriptos = new ArrayList<>();
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

    public int getCupoMaximo() {
        return cupoMaximo;
    }
    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setAlumnosInscriptos(ArrayList<Alumno> alumnosInscriptos) {
        this.alumnosInscriptos = alumnosInscriptos;
    }

    public ArrayList<Alumno> getAlumnosInscriptos() {
        return alumnosInscriptos;
    }

    public int getCupoDisponible() {
        return cupoMaximo - alumnosInscriptos.size();
    }

    public boolean tieneCupo() {
        return getCupoDisponible() > 0;
    }

    public void mostrarAlumnosInscriptos() {
        if (alumnosInscriptos.isEmpty()) {
            System.out.println("No hay alumnos inscriptos.");
            return;
        }
        for (Alumno alumno : alumnosInscriptos) {
            System.out.println(alumno.getNombre()+ " " + alumno.getApellido());
        }
    }
}