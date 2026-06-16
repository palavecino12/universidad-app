package com.universidad.gestores;

import com.universidad.clases.Materia;
import com.universidad.dao.MateriaDAO;
import com.universidad.exceptions.MateriaNoEncontradaException;

import java.util.List;

public class GestorMaterias {

    private MateriaDAO materiaDAO;

    public GestorMaterias() {
        this.materiaDAO = new MateriaDAO();
    }

    //METODOS
    public Materia buscarPorId(int id) {

        return materiaDAO.buscarPorId(id);

    }

    public void registrarMateria(Materia materia) {

        Materia existente = materiaDAO.buscarPorCodigo(materia.getCodigo());
        if (existente != null) {
            System.out.println("Ya existe una materia con ese código.");
            return;
        }
        materiaDAO.crearMateria(materia);
    }

    public void modificarMateria(Materia materia) throws MateriaNoEncontradaException {

        Materia existente = materiaDAO.buscarPorCodigo(materia.getCodigo());
        if (existente == null) {
            throw new MateriaNoEncontradaException();
        }
        materiaDAO.modificarMateria(materia);
    }

    public void eliminarMateria(String codigo) throws MateriaNoEncontradaException {

        Materia existente = materiaDAO.buscarPorCodigo(codigo);
        if (existente == null) {
            throw new MateriaNoEncontradaException();
        }
        // TODO:
        // En la etapa de inscripciones verificar
        // que no existan alumnos inscriptos.
        materiaDAO.eliminarMateria(codigo);
    }

    public List<Materia> listarMaterias() {
        return materiaDAO.listarMaterias();
    }

    public Materia buscarPorCodigo(String codigo) throws MateriaNoEncontradaException {
        Materia existente = materiaDAO.buscarPorCodigo(codigo);
        if (existente == null) {
            throw new MateriaNoEncontradaException();
        }
        return materiaDAO.buscarPorCodigo(codigo);
    }

}