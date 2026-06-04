package com.universidad.clases;

import java.time.LocalDate;

public class Alumno extends Persona {

    private int id;
    private LocalDate fechaNacimiento;

    public Alumno() {
    }

    public Alumno(int id, String nombre, String apellido,
                  String ci, LocalDate fechaNacimiento,
                  String email) {

        super(ci, nombre, apellido, email);
        this.id = id;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", ci='" + ci + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", email='" + email + '\'' +
                '}';
    }
}
