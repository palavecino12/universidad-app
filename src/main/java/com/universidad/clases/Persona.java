package com.universidad.clases;

public abstract class Persona {

    protected String nombre;
    protected String apellido;
    protected String ci;
    protected String email;

    public Persona() {
    }

    public Persona(String ci, String nombre,
                   String apellido, String email) {

        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}