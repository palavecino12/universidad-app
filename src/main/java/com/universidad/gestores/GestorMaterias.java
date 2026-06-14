package com.universidad.gestores;

import com.universidad.clases.Materia;
import com.universidad.dao.MateriaDAO;

import java.util.List;

public class GestorMaterias {

    private MateriaDAO materiaDAO;

    public GestorMaterias() {
        this.materiaDAO = new MateriaDAO();
    }

    public void registrarMateria(Materia materia) {

        Materia existente = materiaDAO.buscarPorCodigo(materia.getCodigo());

        if (existente != null) {
            System.out.println("Ya existe una materia con ese código.");
            return;
        }

        materiaDAO.crearMateria(materia);
    }

    public void modificarMateria(Materia materia) {

        Materia existente = materiaDAO.buscarPorCodigo(materia.getCodigo());

        if (existente == null) {
            System.out.println("La materia no existe.");
            return;
        }

        materiaDAO.modificarMateria(materia);
    }

    public void eliminarMateria(String codigo) {

        Materia existente = materiaDAO.buscarPorCodigo(codigo);

        if (existente == null) {
            System.out.println("La materia no existe.");
            return;
        }

        materiaDAO.eliminarMateria(codigo);
    }

    public List<Materia> listarMaterias() {

        return materiaDAO.listarMaterias();

    }

}