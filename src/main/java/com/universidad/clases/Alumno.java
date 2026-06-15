package com.universidad.clases;

import com.universidad.interfaces.Inscribible;
import java.util.ArrayList;

public class Alumno extends Persona implements Inscribible {

    private int id;
    private String fechaNacimiento;
    private String email;
    private ArrayList<Materia> materiasInscriptas;
    private ArrayList<Calificacion> calificaciones;

    /**
     * Constructor para crear un alumno nuevo.
     *
     * No recibimos las listas porque un alumno nuevo todavía no tiene materias
     * ni calificaciones.
     *
     * Las inicializamos vacías.
     */
    // Para crear un nuevo alumno
    public Alumno(String nombre, String apellido, String ci, String fechaNacimiento, String email) {
        super(nombre, apellido, ci);

        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.materiasInscriptas = new ArrayList<>();
        this.calificaciones = new ArrayList<>();
    }

    // Para traer un alumno desde la BD
    public Alumno(int id, String nombre, String apellido, String ci, String fechaNacimiento, String email) {
        super(nombre, apellido, ci);
        this.id = id;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.materiasInscriptas = new ArrayList<>();
        this.calificaciones = new ArrayList<>();
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Materia> getMateriasInscriptas() {
        return materiasInscriptas;
    }

    public void setMateriasInscriptas(ArrayList<Materia> materiasInscriptas) {
        this.materiasInscriptas = materiasInscriptas;
    }

    public ArrayList<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(ArrayList<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void mostrarInfo() {

        System.out.println("\n===== INFORMACIÓN DEL ALUMNO =====");

        System.out.println("Nombre: " + getNombre());
        System.out.println("Apellido: " + getApellido());
        System.out.println("CI: " + getCi());
        System.out.println("Fecha de nacimiento: " + fechaNacimiento);
        System.out.println("Email: " + email);

        System.out.println("\n----- Materias Inscriptas -----");

        if (materiasInscriptas.isEmpty()) {

            System.out.println("No posee materias inscriptas.");

        } else {

            for (Materia materia : materiasInscriptas) {

                System.out.println("- " + materia.getNombre());

            }
        }

        System.out.println("\n----- Calificaciones -----");

        if (calificaciones.isEmpty()) {

            System.out.println("No posee calificaciones.");

        } else {

            for (Calificacion calificacion : calificaciones) {

                System.out.println(
                        "- Materia: "
                        + calificacion.getMateria().getNombre()
                        + " | Nota: "
                        + calificacion.getNota()
                );

            }
        }

        System.out.println("================================");
    }

    // ===========================
    // MÉTODOS DE LA INTERFAZ
    // ===========================
    /**
     * Inscribe al alumno en una materia.
     *
     * IMPORTANTE: Actualmente solo modifica la lista que está en memoria.
     *
     * Más adelante la inscripción real se hará mediante:
     *
     * GestorInscripciones + InscripcionDAO
     *
     * porque además habrá que guardar la inscripción en la tabla
     * "inscripciones" de MySQL.
     */
    @Override
    public void inscribirse(Materia materia) {

        // Evitamos duplicados en la lista.
        if (!materiasInscriptas.contains(materia)) {

            materiasInscriptas.add(materia);

        }
    }

    /**
     * Da de baja al alumno de una materia.
     *
     * Igual que el método anterior: por ahora solo elimina de la lista local.
     *
     * Más adelante GestorInscripciones e InscripcionDAO se encargarán de
     * eliminar también el registro de la base de datos.
     */
    @Override
    public void darseDeBaja(Materia materia) {

        materiasInscriptas.remove(materia);

    }

    public boolean estaInscripto(Materia materia) {

        for (Materia m : materiasInscriptas) {

            if (m.getCodigo().equals(materia.getCodigo())) {

                return true;

            }

        }

        return false;

    }

    //Modificamos el metodo por defecto del objeto para poder mostrar la informacion mas facil mas adelante.
    @Override
    public String toString() {
        return "Nombre: " + getNombre()
                + " | Apellido: " + getApellido()
                + " | CI: " + getCi()
                + " | Email: " + email;
    }
}
