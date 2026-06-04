package com.universidad.gestores;

import com.universidad.clases.Materia;
import com.universidad.dao.MateriaDAO;
import java.util.List;

public class GestorMaterias {
     private final MateriaDAO materiaDAO;

    public GestorMaterias(MateriaDAO materiaDAO) {
        this.materiaDAO = materiaDAO;
    }

    public void crearMateria(Materia materia) {
        materiaDAO.crearMateria(materia);
    }

    public List<Materia> listarMaterias() {
        return materiaDAO.listarMaterias();
    }

    public Materia buscarPorCodigo(String codigo) {
        return materiaDAO.buscarPorCodigo(codigo);
    }
}
