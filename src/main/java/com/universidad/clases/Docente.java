package com.universidad.clases;

public class Docente extends Persona {

    private String especialidad;

    public Docente(String nombre,String apellido,String ci,String especialidad){
        super(nombre,apellido,ci);
        this.especialidad=especialidad;
    }

    public String getEspecialidad(){
        return especialidad;
    }
    public void setEspecialidad(String especialidad){
        this.especialidad=especialidad;
    }

    @Override
    public void mostrarInfo() {

        System.out.println("\n===== INFORMACIÓN DEL DOCENTE =====");

        System.out.println("Nombre: " + getNombre());
        System.out.println("Apellido: " + getApellido());
        System.out.println("CI: " + getCi());
        System.out.println("Especialidad: " + especialidad);

        System.out.println("==================================");
    }
}
