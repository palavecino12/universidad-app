package com.universidad.clases;

import com.universidad.clases.Persona;

public class Docente extends Persona {

    private String especialidad;

    public String getEspecialidad(){
        return especialidad;
    }

    public void setEspecialidad(String especialidad){
        this.especialidad=especialidad;
    }

    @Override
    public void mostrarInfo(){
        System.out.println("Nombre: "+getNombre());
        System.out.println("Apellido: "+getApellido());
        System.out.println("CI: "+getCi());
        System.out.println("Especialidad: "+especialidad);

    }
}
